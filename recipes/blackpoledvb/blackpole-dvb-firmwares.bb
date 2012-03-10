DESCRIPTION = "DVB firmwares"

RDEPENDS_${PN} = " \
	blackpole-dvb-fw-dib0700 \
	blackpole-dvb-fw-drxk \
	blackpole-dvb-fw-xc5000 \
	blackpole-dvb-fw-dibusb \
	blackpole-dvb-fw-vp7045 \
	blackpole-dvb-fw-vp702x \
	blackpole-dvb-fw-dtt200u \
	blackpole-dvb-fw-wt220u \
	blackpole-dvb-fw-a800 \
	blackpole-dvb-fw-ec168 \
	blackpole-dvb-fw-af9015 \
	blackpole-dvb-fw-af9013 \
	blackpole-dvb-fw-siano \
	blackpole-dvb-fw-novatusb2 \
	blackpole-dvb-fw-xc3028 \
	blackpole-dvb-fw-af9005 \
	blackpole-dvb-fw-as102 \
	blackpole-dvb-fw-af9035 \
	blackpole-dvb-fw-digitv \
	blackpole-dvb-fw-dw2102 \
	blackpole-dvb-fw-it913x \
	blackpole-dvb-fw-tda10071 \
        "

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"

