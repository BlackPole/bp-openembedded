require blackpole-dvb-fw-git.inc

DESCRIPTION = "DVB firmwares"

PV = "1.0"
PR = "r1"

ALLOW_EMPTY_${PN} = "1"

do_install() {
        install -d ${D}${base_libdir}/firmware
        install -m 0644 ${S}/dvb_nova_12mhz_b0.inp ${D}${base_libdir}/firmware
        install -m 0644 ${S}/isdbt_nova_12mhz_b0.inp ${D}${base_libdir}/firmware
        install -m 0644 ${S}/sms1xxx-hcw-114xxx-cmmb-01.fw ${D}${base_libdir}/firmware
        install -m 0644 ${S}/sms1xxx-hcw-55xxx-dvbt-01.fw ${D}${base_libdir}/firmware
        install -m 0644 ${S}/sms1xxx-hcw-55xxx-dvbt-02.fw ${D}${base_libdir}/firmware
        install -m 0644 ${S}/sms1xxx-hcw-55xxx-dvbt-03.fw ${D}${base_libdir}/firmware
        install -m 0644 ${S}/sms1xxx-hcw-55xxx-isdbt-02.fw ${D}${base_libdir}/firmware
        install -m 0644 ${S}/sms1xxx-hcw-55xxx-isdbt-03.fw ${D}${base_libdir}/firmware
        install -m 0644 ${S}/sms1xxx-nova-a-dvbt-01.fw ${D}${base_libdir}/firmware
        install -m 0644 ${S}/sms1xxx-nova-b-dvbt-01.fw ${D}${base_libdir}/firmware
}
