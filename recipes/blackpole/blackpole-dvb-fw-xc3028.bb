DESCRIPTION = "DVB firmwares"

SRC_URI = " \
	http://steventoth.net/linux/hvr1500/xc3028-v27.fw \
	http://steventoth.net/linux/hvr1400/xc3028L-v36.fw \
	"

S = "${WORKDIR}"

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"

do_install() {
        install -d ${D}${base_libdir}/firmware
        install -m 0644 ${S}/xc3028* ${D}${base_libdir}/firmware
}
