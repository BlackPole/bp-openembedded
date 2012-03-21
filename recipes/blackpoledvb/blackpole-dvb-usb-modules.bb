DESCRIPTION = "USB DVB driver"

DVBPROVIDER ?= "kernel"

RDEPENDS_${PN} = " \
	${DVBPROVIDER}-module-dvb-ttusb-budget \
        ${DVBPROVIDER}-module-dvb-usb \
        ${DVBPROVIDER}-module-dvb-usb-a800 \
        ${DVBPROVIDER}-module-dvb-usb-af9005 \
        ${DVBPROVIDER}-module-dvb-usb-af9005-remote \
        ${DVBPROVIDER}-module-dvb-usb-af9015 \
        ${DVBPROVIDER}-module-dvb-usb-anysee \
        ${DVBPROVIDER}-module-dvb-usb-au6610 \
        ${DVBPROVIDER}-module-dvb-usb-az6027 \
        ${DVBPROVIDER}-module-dvb-usb-ce6230 \
        ${DVBPROVIDER}-module-dvb-usb-cinergyt2 \
        ${DVBPROVIDER}-module-dvb-usb-cxusb \
        ${DVBPROVIDER}-module-dvb-usb-dib0700 \
        ${DVBPROVIDER}-module-dvb-usb-dibusb-common \
        ${DVBPROVIDER}-module-dvb-usb-dibusb-mb \
        ${DVBPROVIDER}-module-dvb-usb-dibusb-mc \
        ${DVBPROVIDER}-module-dvb-usb-digitv \
        ${DVBPROVIDER}-module-dvb-usb-dtt200u \
        ${DVBPROVIDER}-module-dvb-usb-dtv5100 \
        ${DVBPROVIDER}-module-dvb-usb-dw2102 \
        ${DVBPROVIDER}-module-dvb-usb-ec168 \
        ${DVBPROVIDER}-module-dvb-usb-friio \
        ${DVBPROVIDER}-module-dvb-usb-gl861 \
        ${DVBPROVIDER}-module-dvb-usb-gp8psk \
        ${DVBPROVIDER}-module-dvb-usb-lmedm04 \
        ${DVBPROVIDER}-module-dvb-usb-m920x \
        ${DVBPROVIDER}-module-dvb-usb-nova-t-usb2 \
        ${DVBPROVIDER}-module-dvb-usb-opera \
        ${DVBPROVIDER}-module-dvb-usb-technisat-usb2 \
        ${DVBPROVIDER}-module-dvb-usb-ttusb2 \
        ${DVBPROVIDER}-module-dvb-usb-umt-010 \
        ${DVBPROVIDER}-module-dvb-usb-vp702x \
        ${DVBPROVIDER}-module-dvb-usb-vp7045 \
	${DVBPROVIDER}-module-dvb-usb-af9035 \
	${DVBPROVIDER}-module-dvb-usb-a867 \
        "

PV = "1.1"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"

