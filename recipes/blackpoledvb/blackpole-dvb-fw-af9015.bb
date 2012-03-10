DESCRIPTION = "DVB firmwares"

SRC_URI = "http://palosaari.fi/linux/v4l-dvb/firmware/af9015/5.1.0.0/dvb-usb-af9015.fw"

S = "${WORKDIR}"

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"

do_install() {
        install -d ${D}${base_libdir}/firmware
        install -m 0644 ${S}/dvb-usb-af9015.fw ${D}${base_libdir}/firmware
}
