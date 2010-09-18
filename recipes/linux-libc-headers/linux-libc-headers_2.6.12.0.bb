SECTION = "devel"
DESCRIPTION = "Sanitized set of 2.6 kernel headers for the C library's use."
HOMEPAGE = "http://ep09.pld-linux.org/~mmazur/linux-libc-headers/"
# license note from the package: 
#   Linux-libc-headers are derived from linux kernel headers. For license of a
#   particular header, check it's content, and if copyright notice isn't present,
#   standard linux kernel license applies. 
# since we assume GPL for linux i think we can also assume it here
LICENSE = "GPL"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"
INHIBIT_DEFAULT_DEPS = "1"
PR = "r1"

SRC_URI = "http://ep09.pld-linux.org/~mmazur/linux-libc-headers/linux-libc-headers-${PV}.tar.bz2 \
	file://keyboard.patch"

def get_nptl_patches(bb, d):
	addons = bb.data.getVar('GLIBC_ADDONS', d, 1)
	if not addons:
		return ""
	if 'nptl' in addons.split(','):
		return "file://linux-libc-headers-2.6.12.0-mips_tls-1.patch;patch=1;pnum=1"
	return ""

SRC_URI_append_mipsel = " file://linux-libc-headers-2.6.12.0-mips_atomic_fix-1.patch;patch=1;pnum=1 \
	${@get_nptl_patches(bb, d)}"

S = "${WORKDIR}/linux-libc-headers-${PV}"

do_configure () {
	case ${TARGET_ARCH} in
		alpha*)   ARCH=alpha ;;
		arm*)     ARCH=arm ;;
		cris*)    ARCH=cris ;;
		hppa*)    ARCH=parisc ;;
		i*86*)    ARCH=i386 ;;
		ia64*)    ARCH=ia64 ;;
		mips*)    ARCH=mips ;;
		m68k*)    ARCH=m68k ;;
		powerpc*) ARCH=ppc ;;
		s390*)    ARCH=s390 ;;
		sh*)      ARCH=sh ;;
		sparc64*) ARCH=sparc64 ;;
		sparc*)   ARCH=sparc ;;
		x86_64*)  ARCH=x86_64 ;;
	esac
	if test !  -e include/asm-$ARCH; then
		oefatal unable to create asm symlink in kernel headers
	fi
	cp -pPR "include/asm-$ARCH" "include/asm"
	if test "$ARCH" = "arm"; then
		cp -pPR include/asm/arch-ebsa285 include/asm/arch
	elif test "$ARCH" = "sh"; then
		cp -pPR include/asm/cpu-${TARGET_ARCH} include/asm/cpu || die "unable to create include/asm/cpu"
	fi
}


do_install() {
	install -d ${D}${includedir}
	cp -pfLR include/linux ${D}${includedir}/
	cp -pfLR include/asm ${D}${includedir}/
}

