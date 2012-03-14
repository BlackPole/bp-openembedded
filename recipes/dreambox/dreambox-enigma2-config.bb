DESCRIPTION = "Dreambox box-specific configuration files"
SECTION = "base"
PRIORITY = "required"
MAINTAINER = "PLi team"
PV = "1.0"
PR = "r1"

SRC_URI = "file://skin_box.xml"

PACKAGES = "${PN}"
FILES_${PN} = "/usr/share/enigma2"
PACKAGE_ARCH = "all"
S = "${WORKDIR}"

do_install() {
	install -d ${D}/usr/share/enigma2
	install -m 644 ${S}/skin_box.xml ${D}/usr/share/enigma2/
}
