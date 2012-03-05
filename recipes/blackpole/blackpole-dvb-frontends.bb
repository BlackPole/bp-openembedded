DESCRIPTION = "DVB frontneds"

DVBPROVIDER ?= "kernel"

RDEPENDS_${PN} = " \
		${DVBPROVIDER}-module-zl10353 \
		${DVBPROVIDER}-module-zl10039 \
		${DVBPROVIDER}-module-zl10036 \
		${DVBPROVIDER}-module-xc5000 \
		${DVBPROVIDER}-module-xc4000 \
		${DVBPROVIDER}-module-ves1x93 \
		${DVBPROVIDER}-module-ves1820 \
		${DVBPROVIDER}-module-tuner-xc2028 \
		${DVBPROVIDER}-module-tua6100 \
		${DVBPROVIDER}-module-tea5767 \
		${DVBPROVIDER}-module-tea5761 \
		${DVBPROVIDER}-module-tda9887 \
		${DVBPROVIDER}-module-tda8290 \
		${DVBPROVIDER}-module-tda827x \
		${DVBPROVIDER}-module-tda826x \
		${DVBPROVIDER}-module-tda8261 \
		${DVBPROVIDER}-module-tda8083 \
		${DVBPROVIDER}-module-tda665x \
		${DVBPROVIDER}-module-tda18271c2dd \
		${DVBPROVIDER}-module-tda18271 \
		${DVBPROVIDER}-module-tda18218 \
		${DVBPROVIDER}-module-tda18212 \
		${DVBPROVIDER}-module-tda10086 \
		${DVBPROVIDER}-module-tda1004x \
		${DVBPROVIDER}-module-tda10048 \
		${DVBPROVIDER}-module-tda10023 \
                ${DVBPROVIDER}-module-tda10021 \
                ${DVBPROVIDER}-module-stv6110x \
                ${DVBPROVIDER}-module-stv6110 \
                ${DVBPROVIDER}-module-stv090x \
                ${DVBPROVIDER}-module-stv0900 \
                ${DVBPROVIDER}-module-stv0367 \
                ${DVBPROVIDER}-module-stv0299 \
                ${DVBPROVIDER}-module-stv0297 \
                ${DVBPROVIDER}-module-stv0288 \
                ${DVBPROVIDER}-module-stb6100 \
                ${DVBPROVIDER}-module-stb6000 \
                ${DVBPROVIDER}-module-stb0899 \
                ${DVBPROVIDER}-module-sp887x \
                ${DVBPROVIDER}-module-sp8870 \
                ${DVBPROVIDER}-module-s921 \
                ${DVBPROVIDER}-module-s5h1432 \
                ${DVBPROVIDER}-module-s5h1420 \
                ${DVBPROVIDER}-module-s5h1411 \
                ${DVBPROVIDER}-module-s5h1409 \
                ${DVBPROVIDER}-module-qt1010 \
                ${DVBPROVIDER}-module-or51211 \
                ${DVBPROVIDER}-module-or51132 \
                ${DVBPROVIDER}-module-nxt6000 \
                ${DVBPROVIDER}-module-nxt200x \
                ${DVBPROVIDER}-module-mxl5007t \
                ${DVBPROVIDER}-module-mxl5005s \
                ${DVBPROVIDER}-module-mt352 \
                ${DVBPROVIDER}-module-mt312 \
                ${DVBPROVIDER}-module-mt2266 \
                ${DVBPROVIDER}-module-mt2131 \
		${DVBPROVIDER}-module-mt20xx \
                ${DVBPROVIDER}-module-mt2060 \
                ${DVBPROVIDER}-module-mc44s803 \
                ${DVBPROVIDER}-module-mb86a20s \
                ${DVBPROVIDER}-module-mb86a16 \
                ${DVBPROVIDER}-module-max2165 \
                ${DVBPROVIDER}-module-lnbp21 \
                ${DVBPROVIDER}-module-lgs8gxx \
                ${DVBPROVIDER}-module-lgs8gl5 \
                ${DVBPROVIDER}-module-lgdt330x \
                ${DVBPROVIDER}-module-lgdt3305 \
                ${DVBPROVIDER}-module-l64781 \
                ${DVBPROVIDER}-module-ix2505v \
                ${DVBPROVIDER}-module-itd1000 \
                ${DVBPROVIDER}-module-isl6423 \
                ${DVBPROVIDER}-module-isl6421 \
                ${DVBPROVIDER}-module-isl6405 \
                ${DVBPROVIDER}-module-ec100 \
                ${DVBPROVIDER}-module-ds3000 \
                ${DVBPROVIDER}-module-drxk \
                ${DVBPROVIDER}-module-drxd \
                ${DVBPROVIDER}-module-dibx000-common \
                ${DVBPROVIDER}-module-dib9000 \
                ${DVBPROVIDER}-module-dib8000 \
                ${DVBPROVIDER}-module-dib7000p \
                ${DVBPROVIDER}-module-dib7000m \
                ${DVBPROVIDER}-module-dib3000mc \
                ${DVBPROVIDER}-module-dib3000mb \
                ${DVBPROVIDER}-module-dib0090 \
                ${DVBPROVIDER}-module-dib0070 \
                ${DVBPROVIDER}-module-cxd2820r \
                ${DVBPROVIDER}-module-cx24123 \
                ${DVBPROVIDER}-module-cx24116 \
                ${DVBPROVIDER}-module-cx24113 \
                ${DVBPROVIDER}-module-cx24110 \
                ${DVBPROVIDER}-module-cx22702 \
                ${DVBPROVIDER}-module-cx22700 \
                ${DVBPROVIDER}-module-bcm3510 \
                ${DVBPROVIDER}-module-au8522 \
                ${DVBPROVIDER}-module-atbm8830 \
                ${DVBPROVIDER}-module-af9013 \
		"
PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"

