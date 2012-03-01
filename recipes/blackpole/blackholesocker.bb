DESCRIPTION="Blackholesocker: External bin to execute commands sent by Enigma2 via socket"
LICENSE = "Gpl2"
MAINTAINER = "meo <meo@sourceforge.net>"

PR = "r0"
PV = "0.1"

BRANCH = "master"
SRCREV = ""

SRC_URI="file://blackholesocker.tar.gz"


S = "${WORKDIR}/blackholesocker"

inherit autotools update-rc.d

INITSCRIPT_NAME = "${PN}"
INITSCRIPT_PARAMS = "defaults"

FILES_${PN} = "/"

do_install() {

	install -d ${D}/usr/bin
	install -m 0755 ${S}/blackholesocker ${D}/usr/bin/blackholesocker
	install -d ${D}/etc/init.d
	install -m 0755 ${S}/${PN}.sh ${D}/etc/init.d/${PN}
}
