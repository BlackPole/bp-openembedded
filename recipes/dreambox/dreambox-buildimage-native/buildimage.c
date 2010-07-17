/*
 * buildimage - create Dreambox nand boot image.
 *
 * contains algorithms ripped from u-boot and first-stage.
 * Licensed as GPL.
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <netinet/in.h>

int eraseblock_size, spare_size, sector_size, largepage;

#define SECTOR_SIZE_WITH_ECC (sector_size+spare_size)


/*
 * Pre-calculated 256-way 1 byte column parity
 */
static const unsigned char nand_ecc_precalc_table[] = {
	0x00, 0x55, 0x56, 0x03, 0x59, 0x0c, 0x0f, 0x5a, 0x5a, 0x0f, 0x0c, 0x59, 0x03, 0x56, 0x55, 0x00,
	0x65, 0x30, 0x33, 0x66, 0x3c, 0x69, 0x6a, 0x3f, 0x3f, 0x6a, 0x69, 0x3c, 0x66, 0x33, 0x30, 0x65,
	0x66, 0x33, 0x30, 0x65, 0x3f, 0x6a, 0x69, 0x3c, 0x3c, 0x69, 0x6a, 0x3f, 0x65, 0x30, 0x33, 0x66,
	0x03, 0x56, 0x55, 0x00, 0x5a, 0x0f, 0x0c, 0x59, 0x59, 0x0c, 0x0f, 0x5a, 0x00, 0x55, 0x56, 0x03,
	0x69, 0x3c, 0x3f, 0x6a, 0x30, 0x65, 0x66, 0x33, 0x33, 0x66, 0x65, 0x30, 0x6a, 0x3f, 0x3c, 0x69,
	0x0c, 0x59, 0x5a, 0x0f, 0x55, 0x00, 0x03, 0x56, 0x56, 0x03, 0x00, 0x55, 0x0f, 0x5a, 0x59, 0x0c,
	0x0f, 0x5a, 0x59, 0x0c, 0x56, 0x03, 0x00, 0x55, 0x55, 0x00, 0x03, 0x56, 0x0c, 0x59, 0x5a, 0x0f,
	0x6a, 0x3f, 0x3c, 0x69, 0x33, 0x66, 0x65, 0x30, 0x30, 0x65, 0x66, 0x33, 0x69, 0x3c, 0x3f, 0x6a,
	0x6a, 0x3f, 0x3c, 0x69, 0x33, 0x66, 0x65, 0x30, 0x30, 0x65, 0x66, 0x33, 0x69, 0x3c, 0x3f, 0x6a,
	0x0f, 0x5a, 0x59, 0x0c, 0x56, 0x03, 0x00, 0x55, 0x55, 0x00, 0x03, 0x56, 0x0c, 0x59, 0x5a, 0x0f,
	0x0c, 0x59, 0x5a, 0x0f, 0x55, 0x00, 0x03, 0x56, 0x56, 0x03, 0x00, 0x55, 0x0f, 0x5a, 0x59, 0x0c,
	0x69, 0x3c, 0x3f, 0x6a, 0x30, 0x65, 0x66, 0x33, 0x33, 0x66, 0x65, 0x30, 0x6a, 0x3f, 0x3c, 0x69,
	0x03, 0x56, 0x55, 0x00, 0x5a, 0x0f, 0x0c, 0x59, 0x59, 0x0c, 0x0f, 0x5a, 0x00, 0x55, 0x56, 0x03,
	0x66, 0x33, 0x30, 0x65, 0x3f, 0x6a, 0x69, 0x3c, 0x3c, 0x69, 0x6a, 0x3f, 0x65, 0x30, 0x33, 0x66,
	0x65, 0x30, 0x33, 0x66, 0x3c, 0x69, 0x6a, 0x3f, 0x3f, 0x6a, 0x69, 0x3c, 0x66, 0x33, 0x30, 0x65,
	0x00, 0x55, 0x56, 0x03, 0x59, 0x0c, 0x0f, 0x5a, 0x5a, 0x0f, 0x0c, 0x59, 0x03, 0x56, 0x55, 0x00
};


/*
 * Creates non-inverted ECC code from line parity
 */
static void nand_trans_result(unsigned char reg2, unsigned char reg3,
	unsigned char *ecc_code)
{
	unsigned char a, b, i, tmp1, tmp2;

	/* Initialize variables */
	a = b = 0x80;
	tmp1 = tmp2 = 0;

	/* Calculate first ECC byte */
	for (i = 0; i < 4; i++) {
		if (reg3 & a)		/* LP15,13,11,9 --> ecc_code[0] */
			tmp1 |= b;
		b >>= 1;
		if (reg2 & a)		/* LP14,12,10,8 --> ecc_code[0] */
			tmp1 |= b;
		b >>= 1;
		a >>= 1;
	}

	/* Calculate second ECC byte */
	b = 0x80;
	for (i = 0; i < 4; i++) {
		if (reg3 & a)		/* LP7,5,3,1 --> ecc_code[1] */
			tmp2 |= b;
		b >>= 1;
		if (reg2 & a)		/* LP6,4,2,0 --> ecc_code[1] */
			tmp2 |= b;
		b >>= 1;
		a >>= 1;
	}

	/* Store two of the ECC bytes */
	ecc_code[0] = tmp1;
	ecc_code[1] = tmp2;
}

/*
 * Calculate 3 byte ECC code for 256 byte block
 */
static void nand_calculate_ecc (const unsigned char *dat, unsigned char *ecc_code)
{
	unsigned char idx, reg1, reg3;
	int j;

	/* Initialize variables */
	reg1 = reg3 = 0;
	ecc_code[0] = ecc_code[1] = ecc_code[2] = 0;

	/* Build up column parity */
	for(j = 0; j < 256; j++) {

		/* Get CP0 - CP5 from table */
		idx = nand_ecc_precalc_table[dat[j]];
		reg1 ^= idx;

		/* All bit XOR = 1 ? */
		if (idx & 0x40) {
			reg3 ^= (unsigned char) j;
		}
	}

	/* Create non-inverted ECC code from line parity */
	nand_trans_result((reg1 & 0x40) ? ~reg3 : reg3, reg3, ecc_code);

	/* Calculate final ECC code */
	ecc_code[0] = ~ecc_code[0];
	ecc_code[1] = ~ecc_code[1];
	ecc_code[2] = ((~reg1) << 2) | 0x03;
}

void file_open(FILE **f, int *size, const char *filename)
{
	*f = fopen(filename, "r");
	if (!*f)
	{
		perror(filename);
		exit(1);
	}
	fseek(*f, 0, SEEK_END);
	*size = ftell(*f);
	fseek(*f, 0, SEEK_SET);
}

void die(const char *msg)
{
	fprintf(stderr, "%s\n", msg);
	exit(2);
}

void emit_4(unsigned long val)
{
	val = htonl(val);
	write(1, &val, 4);
}

#define TO_SECT(x) (x+sector_size-1)/sector_size

typedef void fnc_encode_ecc(unsigned char *dst, unsigned char *src, int cnt);

void encode_hevers(unsigned char *dst, unsigned char *src, int count)
{
	if (!largepage)
	{
		dst[0] = count >> 8;
		dst[1] = count & 0xFF;
		unsigned char temp;
		int cnt;
		for(cnt=0; cnt<sector_size; cnt++)
		{
			temp=src[cnt];
			dst[2]^=temp;
			if(cnt & 1) 
				dst[6 + 0]^=temp;
			else 
				dst[6 + 1]^=temp;
			if(cnt & 2) dst[6 + 2]^=temp;
			if(cnt & 4) dst[6 + 3]^=temp;
			if(cnt & 8) dst[6 + 4]^=temp;
			if(cnt & 16) dst[6 + 5]^=temp;
			if(cnt & 32) dst[6 + 6]^=temp;
			if(cnt & 64) dst[6 + 7]^=temp;
			if(cnt & 128) dst[6 + 8]^=temp;
			if(cnt & 256) dst[6 + 9]^=temp;
		}
	} else
	{
		dst[0] = 0xFF;
		dst[1] = 0xFF;
		dst[2] = count >> 8;
		dst[3] = count & 0xFF;
		unsigned char temp;
		int cnt;
		for(cnt=0; cnt<sector_size; cnt++)
		{
			temp=src[cnt];
			dst[40]^=temp;
			if(cnt & 1) 
				dst[41]^=temp;
			else 
				dst[42]^=temp;
			if(cnt & 2) dst[43]^=temp;
			if(cnt & 4) dst[44]^=temp;
			if(cnt & 8) dst[45]^=temp;
			if(cnt & 16) dst[46]^=temp;
			if(cnt & 32) dst[47]^=temp;
			if(cnt & 64) dst[48]^=temp;
			if(cnt & 128) dst[49]^=temp;
			if(cnt & 256) dst[50]^=temp;
		}
	}
}

void encode_jffs2(unsigned char *dst, unsigned char *src, int cnt)
{
	memset(dst, 0xFF, spare_size);

	if (!largepage)
	{
		unsigned char ecc_code[8];
		nand_calculate_ecc (src, ecc_code);
		nand_calculate_ecc (src+256, ecc_code+3);
		dst[0] = ecc_code[0];
		dst[1] = ecc_code[1];
		dst[2] = ecc_code[2];
		dst[3] = ecc_code[3];
		dst[4] = 0xFF;
		dst[5] = 0xFF;
		dst[6] = ecc_code[4];
		dst[7] = ecc_code[5];

		if (!(cnt & ((eraseblock_size/sector_size)-1)))
		{
			dst[8]  = 0x19;
			dst[9]  = 0x85;
			dst[10] = 0x20;
			dst[11] = 0x03;
			dst[12] = 0x00;
			dst[13] = 0x00;
			dst[14] = 0x00;
			dst[15] = 0x08;
		} else
			memset(dst + 8, 0xFF, 8);
	} else
	{
		int i;
		for (i=0; i<8; ++i)
			nand_calculate_ecc (src + i * 256, dst + 40 + i * 3);

		if (!(cnt & ((eraseblock_size/sector_size)-1)))
		{
			dst[2] = 0x19;
			dst[3] = 0x85;
			dst[4] = 0x20;
			dst[5] = 0x03;
			dst[6] = 0x00;
			dst[7] = 0x00;
			dst[8] = 0x00;
			dst[9] = 0x08;
		}
	}
}

void emit_file(FILE *src, int size, fnc_encode_ecc * eccfnc)
{
	emit_4(size * SECTOR_SIZE_WITH_ECC);
	int cnt = 0;
	while (1)
	{
		unsigned char sector[sector_size + spare_size];
		memset(sector, 0xFF, sector_size + spare_size);
		int r = fread(sector, 1, sector_size, src);
		if (!r)
			break;
		eccfnc(sector + sector_size, sector, cnt);
		write(1, sector, SECTOR_SIZE_WITH_ECC);
		++cnt;
	}
	if (cnt != size)
		die("size changed");
}

	/* reserve to two sectors plus 1% for badblocks, and round down */
#define BADBLOCK_SAFE(x) ( ((x) - (eraseblock_size * 2) - (x) / 100) &~ eraseblock_size )

int main(int argc, char **argv)
{
	if ((argc != 4) && (argc != 5) && (argc != 6) && (argc != 7) )
	{
		fprintf(stderr, "usage: %s <2nd.bin.gz> <boot.jffs2> <root.jffs2> [<arch>] [<flashsize-in-mb>] [options]> image.nfi\n", *argv);
		return 1;
	}

	FILE *f_2nd, *f_boot, *f_root;
	int size_2nd, size_boot, size_root;

	file_open(&f_2nd, &size_2nd, argv[1]);
	file_open(&f_boot, &size_boot, argv[2]);
	file_open(&f_root, &size_root, argv[3]);

	int flashsize = 32*1024*1024;
	if (argc >= 6)
		flashsize = atoi(argv[5]) * 1024 * 1024;


	int partition[] = {0x40000, 0x400000, flashsize};

	if ((argc >= 7) && strstr(argv[6], "large"))
	{
		largepage = 1;
		eraseblock_size = 128*1024;
		spare_size = 64;
		sector_size = 2048;
		partition[0] = 0x100000;
	} else
	{
		largepage = 0;
		eraseblock_size = 16384;
		spare_size = 16;
		sector_size = 512;
		partition[0] = 0x40000;
	}

	fprintf(stderr, "2nd: %u of %u bytes\n", size_2nd, BADBLOCK_SAFE(partition[0]));
	fprintf(stderr, "boot: %u of %u bytes\n", size_boot, BADBLOCK_SAFE(partition[1] - partition[0]));
	fprintf(stderr, "root: %u of %u bytes\n", size_root, BADBLOCK_SAFE(partition[2] - partition[1]));

	if (size_2nd > BADBLOCK_SAFE(partition[0]))
		die("2nd stage is too big. did you gzip it before?");
	if (size_boot > BADBLOCK_SAFE(partition[1] - partition[0]))
		die("boot is too big. You can modify the buildimage tool, but you don't want that.");
	if (size_root > BADBLOCK_SAFE(partition[2] - partition[1]))
		die("root is too big. This doesn't work. sorry.");

	int sectors_2nd = TO_SECT(size_2nd), sectors_boot = TO_SECT(size_boot), sectors_root = TO_SECT(size_root);

	int num_partitions = 3;

	int total_size = 4 + num_partitions * 4 + 4 + sectors_2nd * SECTOR_SIZE_WITH_ECC + 4 + sectors_boot * SECTOR_SIZE_WITH_ECC + 4 + sectors_root * SECTOR_SIZE_WITH_ECC;

		/* in case an architecture is given, write NFI1 header */
	if (argc >= 5)
	{
		char header[32] = "NFI1";
		strncpy(header + 4, argv[4], 28);
		write(1, header, 32);
	}

		/* global header */
	emit_4(total_size);

		/* partition */
	emit_4(num_partitions * 4);
	int i;
	for (i=0; i < num_partitions; ++i)
		emit_4(partition[i]);

		/* 2nd stage */
	emit_file(f_2nd, sectors_2nd, encode_hevers);
		/* boot + root */
	emit_file(f_boot, sectors_boot, encode_jffs2);
	emit_file(f_root, sectors_root, encode_jffs2);

	return 0;
}
