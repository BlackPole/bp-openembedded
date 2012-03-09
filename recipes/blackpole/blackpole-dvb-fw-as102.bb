require blackpole-dvb-fw-git.inc

DESCRIPTION = "DVB firmwares"

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"

do_install() {
        install -d ${D}${base_libdir}/firmware
        install -m 0644 as102_data1_st.hex ${D}${base_libdir}/firmware
        install -m 0644 as102_data2_st.hex ${D}${base_libdir}/firmware
}
