DESCRIPTION = "WBXML parsing and encoding library."
HOMEPAGE = "http://libwbxml.opensync.org/"
SECTION = "libs"
PRIORITY = "optional"
LICENSE = "LGPL"

PR = "r0"

SRC_URI = "${SOURCEFORGE_MIRROR}/libwbxml/libwbxml-${PV}.tar.gz \
           "

SRC_URI[md5sum] = "f5031e9f730ffd9dc6a2d1ded585e1d1"
SRC_URI[sha256sum] = "a057daa098f12838eb4e635bb28413027f1b73819872c3fbf64e3207790a3f7d"

S = "${WORKDIR}/libwbxml-${PV}"

inherit cmake pkgconfig

EXTRA_OECMAKE += " . -B${WORKDIR}/build "

PACKAGES += "${PN}-tools"

FILES_${PN}-tools = "${bindir}"
FILES_${PN} = "${libdir}/*.so.*"

do_build_prepend() {
	cd ${WORKDIR}/build
}

do_install_prepend() {
	cd ${WORKDIR}/build
}
