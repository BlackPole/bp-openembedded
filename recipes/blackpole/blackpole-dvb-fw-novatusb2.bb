DESCRIPTION = "DVB firmwares"

SRC_URI = " \
	http://steventoth.net/linux/nova-t-usb2/dvb-usb-nova-t-usb2-01.fw \
	http://steventoth.net/linux/nova-t-usb2/dvb-usb-nova-t-usb2-02.fw \
	"

S = "${WORKDIR}"

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"

do_install() {
        install -d ${D}${base_libdir}/firmware
        install -m 0644 ${S}/dvb-usb-nova-t-usb2-* ${D}${base_libdir}/firmware
}
