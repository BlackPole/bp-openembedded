DESCRIPTION = "grep GNU utility"
SECTION = "console/utils"
HOMEPAGE = "http://savannah.gnu.org/projects/grep/"
LICENSE = "GPLv3"

PR = "r0"

SRC_URI = "${GNU_MIRROR}/grep/grep-${PV}.tar.gz \
           file://uclibc_libiconv.patch"

SRC_URI[md5sum] = "e848f07e3e79aa7899345d17c7e4115e"
SRC_URI[sha256sum] = "ad14831015a79cea36785aa47415f6c0653a6ac4eaf9c15456f0d9d09c9e1bad"

inherit autotools gettext

BBCLASSEXTEND = "native"

EXTRA_OECONF = "--disable-perl-regexp"

do_configure_prepend () {
	rm -f ${S}/m4/init.m4
}

do_install () {
	autotools_do_install
	install -d ${D}${base_bindir}
	mv ${D}${bindir}/grep ${D}${base_bindir}/grep.${PN}
	mv ${D}${bindir}/egrep ${D}${base_bindir}/egrep.${PN}
	mv ${D}${bindir}/fgrep ${D}${base_bindir}/fgrep.${PN}
}

pkg_postinst_${PN} () {
	update-alternatives --install ${base_bindir}/grep grep grep.${PN} 100
	update-alternatives --install ${base_bindir}/egrep egrep egrep.${PN} 100
	update-alternatives --install ${base_bindir}/fgrep fgrep fgrep.${PN} 100
}

pkg_prerm_${PN} () {
	update-alternatives --remove grep grep.${PN}
	update-alternatives --remove egrep egrep.${PN}
	update-alternatives --remove fgrep fgrep.${PN}
}
