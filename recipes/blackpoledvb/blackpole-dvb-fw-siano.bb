DESCRIPTION = "DVB firmwares"

SRC_URI = " \
	http://steventoth.net/linux/sms1xxx/sms1xxx-hcw-114xxx-cmmb-01.fw \
	http://steventoth.net/linux/sms1xxx/sms1xxx-hcw-55xxx-dvbt-01.fw \
	http://steventoth.net/linux/sms1xxx/sms1xxx-hcw-55xxx-dvbt-02.fw \
	http://steventoth.net/linux/sms1xxx/sms1xxx-hcw-55xxx-dvbt-03.fw \
	http://steventoth.net/linux/sms1xxx/sms1xxx-hcw-55xxx-isdbt-02.fw \
	http://steventoth.net/linux/sms1xxx/sms1xxx-hcw-55xxx-isdbt-03.fw \
	"

S = "${WORKDIR}"

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"

do_install() {
        install -d ${D}${base_libdir}/firmware
        install -m 0644 ${S}/sms1xxx-* ${D}${base_libdir}/firmware
}
