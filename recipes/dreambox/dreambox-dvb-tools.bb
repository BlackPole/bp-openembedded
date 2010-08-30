DESCRIPTION = "Small utilities specific to the dreambox dvb receiver (for DVB v1)"
DEPENDS = "dreambox-dvbincludes"
SECTION = "base"
PRIORITY = "optional"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"
LICENSE = "GPL"
PV = "1.0"
PR = "r0"

PROVIDES += "virtual/showiframe"
RPROVIDES_${PN} += "virtual/showiframe"

SRC_URI = "http://sources.dreamboxupdate.com/download/opendreambox/dreambox-dvb-tools-1.0.tar.gz"

inherit qmake

UTILS = "grabpic showiframe dvbnet test_tbl test_dmx"

do_configure_prepend() {
	cd ${S}/
	echo "TEMPLATE=subdirs" > dmutils.pro
	echo "CONFIG=console" >> dmutils.pro
	echo "SUBDIRS=${UTILS}" >> dmutils.pro
}

do_install() {
	install -d ${D}/${bindir}/
	for u in ${UTILS}
	do
		install -m 0755 ${S}/${u}/${u} ${D}/${bindir}/
	done
}
