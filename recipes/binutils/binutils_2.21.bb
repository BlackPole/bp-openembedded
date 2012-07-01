require binutils.inc
PR = "${INC_PR}.3"
LICENSE = "GPLv3"

SRC_URI = "http://mirrors.usc.edu/pub/gnu/binutils/binutils-${PV}.tar.bz2 \
     file://binutils-uclibc-100-uclibc-conf.patch \
     file://110-arm-eabi-conf.patch \
     file://binutils-uclibc-300-001_ld_makefile_patch.patch \
     file://binutils-uclibc-300-006_better_file_error.patch \
     file://binutils-uclibc-300-012_check_ldrunpath_length.patch \
     file://binutils-uclibc-gas-needs-libm.patch \
     file://fix-clone-conflicts.patch \
     ${@['','file://libtool-2.4-update.patch'][bb.data.getVar('LIBTOOL_HAS_SYSROOT', d, 1) == 'yes']} \
     file://binutils-2.19.1-ld-sysroot.patch \
     "

FILES_${PN}-symlinks += "${bindir}/elfedit"
# When we enable gold this might need to be make conditional
FILES_${PN}-symlinks += "${bindir}/ld.bfd"
