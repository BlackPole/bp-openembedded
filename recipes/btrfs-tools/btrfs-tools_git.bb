SUMMARY = "Checksumming Copy on Write Filesystem utilities"
DESCRIPTION = "Btrfs is a new copy on write filesystem for Linux aimed at \
implementing advanced features while focusing on fault tolerance, repair and \
easy administration. \
This package contains utilities (mkfs, fsck, btrfsctl) used to work with \
btrfs and an utility (btrfs-convert) to make a btrfs filesystem from an ext3."

HOMEPAGE = "https://btrfs.wiki.kernel.org"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=fcb02dc552a041dee27e4b85c7396067"
SECTION = "base"
DEPENDS = "util-linux attr"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/mason/btrfs-progs.git;protocol=git;tag=fdb6c0402337d9607c7a39155088eaf033742752;branch=master"

S = "${WORKDIR}/git"

PR = "r6"

SRC_URI += " file://fix_use_of_gcc.patch \
	 file://weak-defaults.patch \
	 file://fix_race_condition_with_multithreaded_make.patch "

SRC_URI[md5sum] = "78b1700d318de8518abfaab71f99a885"
SRC_URI[sha256sum] = "1285774e0cb72984fac158dd046c8d405324754febd30320cd31e459253e4b65"

do_install () {
	oe_runmake 'DESTDIR=${D}' install
}

BBCLASSEXTEND = "native"
