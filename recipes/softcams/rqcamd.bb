CAMNAME = "rqcamd"
DESCRIPTION = "${CAMNAME} ${PV} softcam"

PN = "enigma2-plugin-softcams-${CAMNAME}"
PV = "1.31"
PR = "r0"

SRC_URI = "http://downloads.pli-images.org/softcams/${CAMNAME}-${PV}-binaries.tar.gz"

S = "${WORKDIR}/"

INHIBIT_PACKAGE_STRIP = "1"

require softcam.inc

CONFFILES = "/usr/bin/${CAMNAME}.conf"

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/${CAMNAME}.mips ${D}/usr/bin/${CAMNAME}
	install -m 0644 ${S}/${CAMNAME}.conf ${D}/usr/bin/${CAMNAME}.conf
}
