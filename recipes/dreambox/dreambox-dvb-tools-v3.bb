DESCRIPTION = "Small utilities specific to the dreambox dvb receiver (for DVB v3)"
SECTION = "base"
PRIORITY = "optional"
MAINTAINER = "Felix Domke <tmbinc@elitedvb.net>"
LICENSE = "GPL"

PROVIDES += "virtual/showiframe"
RPROVIDES_${PN} += "virtual/showiframe"

PV = "1.8"
PR = "r0"

SRC_URI = "http://sources.dreamboxupdate.com/download/opendreambox/dreambox-dvb-tools-v3-${PV}.tar.gz"

inherit qmake

UTILS = "showiframe"

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
