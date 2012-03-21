DESCRIPTION = "Black Pole extra files"
LICENSE = "GPL2"
MAINTAINER = "Meo <lupomeo@gmail.com>"

SRC_URI = "file://Ncam_Ci.sh file://StartBhCam file://Delete_all_Crashlogs.sh file://Ifconfig.sh file://Netstat.sh file://Uptime.sh"

PR = "r0"

FILES_${PN} = "/"

do_compile() {
	echo "${MACHINE}" > ${WORKDIR}/bpmachine
	echo "${BP_DISTRO_NAME} ${BP_DISTRO_VERSION}" > ${WORKDIR}/bpversion
}


do_install() {
	
	mkdir -p ${D}/usr/camscript
	mkdir -p ${D}/usr/script

	install -d ${D}/etc
	install -m 0644 ${WORKDIR}/bpmachine ${D}/etc/bpmachine
	install -m 0644 ${WORKDIR}/bpversion ${D}/etc/bpversion
	

	install -d ${D}/usr/bin
	install -m 0755 ${WORKDIR}/StartBhCam ${D}/usr/bin/StartBhCam

	install -d ${D}/usr/camscript
	install -m 0755 ${WORKDIR}/Ncam_Ci.sh ${D}/usr/camscript/Ncam_Ci.sh
	
	install -d ${D}/usr/script
	for x in /Delete_all_Crashlogs.sh Ifconfig.sh Netstat.sh Uptime.sh; do
		install -m 0755 ${WORKDIR}/$x ${D}/usr/script/$x
	done

}
