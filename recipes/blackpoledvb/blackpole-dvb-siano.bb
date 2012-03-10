DESCRIPTION = "DVB Siano drivers"

DVBPROVIDER ?= "kernel"

RDEPENDS_${PN} = " \
	${DVBPROVIDER}-module-smsdvb \
        ${DVBPROVIDER}-module-smsmdtv \
        ${DVBPROVIDER}-module-smsusb \
        "

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"

