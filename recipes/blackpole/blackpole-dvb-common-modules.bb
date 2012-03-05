DESCRIPTION = "DVB common modules"

DVBPROVIDER ?= "kernel"

RDEPENDS_${PN} = " \
	${DVBPROVIDER}-module-dvb-pll \
        ${DVBPROVIDER}-module-tuner \
        ${DVBPROVIDER}-module-tveeprom \
        ${DVBPROVIDER}-module-videobuf-core \
        ${DVBPROVIDER}-module-videobuf-dvb \
        ${DVBPROVIDER}-module-videobuf-vmalloc \
        ${DVBPROVIDER}-module-tuner-simple \
        ${DVBPROVIDER}-module-tuner-types \
        "

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"

