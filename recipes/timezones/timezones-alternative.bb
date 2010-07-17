DESCRIPTION = "Timezone data, alternative"
SECTION = "base"
PRIORITY = "optional"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"
LICENSE = "GPL"

PV = "2008i"
PR = "r0"

SRC_URI = "file://zoneinfo.tar.bz2"
S = "${WORKDIR}/zoneinfo"

FILES_${PN} = "usr/share/zoneinfo/[A-Z]*"
PACKAGE_ARCH = "all"

do_install() {
	install -d ${D}/usr/share/zoneinfo/
	
	for file in ${S}/*
	do
		[ -f $file ] && install -m 644 "$file" ${D}/usr/share/zoneinfo/
	done;
	true;
}
