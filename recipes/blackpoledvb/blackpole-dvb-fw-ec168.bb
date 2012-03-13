DESCRIPTION = "DVB firmwares"

SRC_URI = "http://palosaari.fi/linux/v4l-dvb/firmware/ec168/dvb-usb-ec168.fw"

S = "${WORKDIR}"

PV = "1.0"
PR = "r1"

ALLOW_EMPTY_${PN} = "1"

do_install() {
        install -d ${D}${base_libdir}/firmware
        install -m 0644 ${S}/dvb-usb-ec168.fw ${D}${base_libdir}/firmware
}
