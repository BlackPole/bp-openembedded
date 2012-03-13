require blackpole-dvb-fw-git.inc

DESCRIPTION = "DVB firmwares"

PV = "1.0"
PR = "r1"

ALLOW_EMPTY_${PN} = "1"

do_install() {
        install -d ${D}${base_libdir}/firmware
        install -m 0644 ${S}/dvb-usb-s660.fw ${D}${base_libdir}/firmware
        install -m 0644 ${S}/dvb-fe-ds3000.fw ${D}${base_libdir}/firmware
}
