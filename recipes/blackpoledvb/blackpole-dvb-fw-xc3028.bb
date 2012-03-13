require blackpole-dvb-fw-git.inc

DESCRIPTION = "DVB firmwares"

PV = "1.0"
PR = "r1"

ALLOW_EMPTY_${PN} = "1"

do_install() {
        install -d ${D}${base_libdir}/firmware
        install -m 0644 ${S}/xc3028-v27.fw ${D}${base_libdir}/firmware
        install -m 0644 ${S}/xc3028L-v36.fw ${D}${base_libdir}/firmware
}
