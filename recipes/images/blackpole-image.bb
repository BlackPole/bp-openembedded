require blackpole-image-base.bb

DVB_USB_DRIVERS = " \
		kernel-module-dvb-usb kernel-module-dvb-usb-a800 kernel-module-dvb-usb-af9005 kernel-module-dvb-usb-af9015 \
		kernel-module-dvb-usb-anysee kernel-module-dvb-usb-au6610 kernel-module-dvb-usb-az6027 kernel-module-dvb-usb-ce6230 \
		kernel-module-dvb-usb-cinergyt2 kernel-module-dvb-usb-cxusb kernel-module-dvb-usb-dib0700 kernel-module-dvb-usb-dibusb-common \
		kernel-module-dvb-usb-dibusb-mb kernel-module-dvb-usb-dibusb-mc kernel-module-dvb-usb-digitv kernel-module-dvb-usb-dtt200u \
		kernel-module-dvb-usb-dtv5100 kernel-module-dvb-usb-dw2102 kernel-module-dvb-usb-ec168 kernel-module-dvb-usb-friio \
		kernel-module-dvb-usb-gl861 kernel-module-dvb-usb-gp8psk kernel-module-dvb-usb-lmedm04 kernel-module-dvb-usb-m920x \
		kernel-module-dvb-usb-nova-t-usb2 kernel-module-dvb-usb-opera kernel-module-dvb-usb-technisat-usb2 kernel-module-dvb-usb-ttusb2 \
		kernel-module-dvb-usb-umt-010 kernel-module-dvb-usb-vp702x kernel-module-dvb-usb-vp7045 kernel-module-dvb-pll \
		kernel-module-dvb-ttusb-budget \
		kernel-module-ds3000 kernel-module-drxk kernel-module-drxd kernel-module-dibx000-common kernel-module-dib9000 \
		kernel-module-dib7000p kernel-module-dib7000m kernel-module-dib3000mc kernel-module-dib3000mb kernel-module-dib0090 \
		kernel-module-dib0070 kernel-module-cxd2820r kernel-module-cx24123 kernel-module-cx24116 kernel-module-cx24113 \
		kernel-module-cx24110 kernel-module-cx22702 kernel-module-cx22700 kernel-module-atbm8830 kernel-module-au8522 kernel-module-af9013 \
		kernel-module-ec100 kernel-module-sp8870 kernel-module-isl6405 kernel-module-isl6421 kernel-module-isl6423 kernel-module-dib8000 \
		kernel-module-lgdt3305 kernel-module-lgdt330x kernel-module-lgs8gl5 kernel-module-lgs8gxx kernel-module-lnbp21 \
		kernel-module-max2165 kernel-module-mb86a16 kernel-module-mb86a20s kernel-module-mc44s803 kernel-module-mt2060 \ 
		kernel-module-mt2131 kernel-module-mt2266 kernel-module-mt312 kernel-module-mt352 kernel-module-mxl5005s kernel-module-mxl5007t \
		kernel-module-nxt200x kernel-module-nxt6000 kernel-module-qt1010 kernel-module-s5h1409 kernel-module-s5h1411 kernel-module-s5h1420 \ 
		kernel-module-s5h1432 kernel-module-s921 kernel-module-smsdvb kernel-module-smsmdtv kernel-module-smsusb kernel-module-stb0899 \
		kernel-module-stb6000 kernel-module-stb6100 kernel-module-stv0288 kernel-module-stv0297 kernel-module-stv0299 \ 
		kernel-module-stv0900 kernel-module-stv090x kernel-module-stv6110 kernel-module-stv6110x kernel-module-tda10021 \ 
		kernel-module-tda10023 kernel-module-tda10048 kernel-module-tda1004x kernel-module-tda10086 kernel-module-tda18212 \
		kernel-module-tda18218 kernel-module-tda18271 kernel-module-tda18271c2dd kernel-module-tda665x kernel-module-tda8083 \
		kernel-module-tda8261 kernel-module-tda826x kernel-module-tda827x kernel-module-tda8290 kernel-module-tda9887 kernel-module-xc5000 \
		kernel-module-tea5761 kernel-module-tea5767 kernel-module-tua6100 kernel-module-ves1820 kernel-module-ves1x93 kernel-module-xc4000 \
		kernel-module-zl10036 kernel-module-zl10039 kernel-module-zl10353 kernel-module-mt20xx kernel-module-stv0367 \
		kernel-module-videobuf-core kernel-module-videobuf-dvb kernel-module-videobuf-vmalloc kernel-module-itd1000 kernel-module-ix2505v \
		kernel-module-bcm3510 \
		kernel-module-tuner kernel-module-tuner-simple kernel-module-tuner-types kernel-module-tuner-xc2028 kernel-module-tveeprom \
		kernel-module-em28xx kernel-module-em28xx-alsa kernel-module-em28xx-dvb \
		"
DVB_USB_FIRMWARE = " \
		firmware-af9005 firmware-as102-data1-st firmware-as102-data2-st \
		firmware-dvb-fe-af9013 firmware-dvb-fe-ds3000 firmware-dvb-fe-tda10071 firmware-dvb-nova-12mhz-b0 \
		firmware-dvb-siano firmware-dvb-usb-af9015 firmware-dvb-usb-af9035-01 \
		firmware-dvb-usb-dib0700-1.20 firmware-xc3028-v27 firmware-xc3028l-v36 \
		firmware-sms1xxx-hcw-55xxx-isdbt-03 \
		firmware-sms1xxx-hcw-55xxx-dvbt-03 \
		firmware-sms1xxx-hcw-55xxx-dvbt-01 firmware-sms1xxx-hcw-114xxx-cmmb-01 firmware-isdbt-nova-12mhz-b0 \
		firmware-dvb-usb-s660 \
		firmware-dvb-usb-it913x firmware-dvb-usb-it9135 \
		firmware-dvb-fe-xc5000-1.1 firmware-dvb-fe-xc5000-1.6.114 \
		"

WIFI_DRIVERS = " \
				${@base_contains("MACHINE_FEATURES", "wifiusblegacy", \
					"rt73 rt3070 rtl8192cu rtl871x", \
					" \
					kernel-module-ath9k-htc \
					kernel-module-carl9170 \
					kernel-module-rtl8187 \
					kernel-module-rtl8192cu \
					kernel-module-r8712u \
					kernel-module-rt2500usb \
					kernel-module-rt2800usb \
					kernel-module-rt73usb \
					kernel-module-zd1211rw \
					firmware-carl9170 \
					firmware-htc9271 \
					firmware-rt2870 \
					", d)} \
				firmware-rt73 \
				firmware-rtl8192cu \
				firmware-rtl8712u \
				firmware-zd1211 \
				"

ENIGMA2_PLUGINS = " \
				enigma2-plugin-extensions-audiosync \
				enigma2-plugin-extensions-autobackup \
				enigma2-plugin-systemplugins-softwaremanager \
				enigma2-plugin-systemplugins-positionersetup \
				enigma2-plugin-extensions-cutlisteditor \
				enigma2-plugin-systemplugins-satfinder \
				enigma2-plugin-systemplugins-videotune \
				enigma2-plugin-extensions-mediascanner \
				enigma2-plugin-extensions-openwebif \
				enigma2-plugin-systemplugins-crossepg \
				enigma2-plugin-extensions-graphmultiepg \
				enigma2-plugin-systemplugins-skinselector \
				enigma2-plugin-extensions-pictureplayer \
				enigma2-plugin-extensions-mediaplayer \
				enigma2-plugin-systemplugins-networkbrowser \
				enigma2-plugin-systemplugins-fastscan \
				enigma2-plugin-systemplugins-osdpositionsetup \
				enigma2-plugin-systemplugins-hdmicec \
				${@base_contains("MACHINE_FEATURES", "nohotplug", "", "enigma2-plugin-systemplugins-hotplug", d)} \
				${@base_contains("MACHINE_FEATURES", "dvb-c", "enigma2-plugin-systemplugins-cablescan" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "frontprocessor", "enigma2-plugin-systemplugins-frontprocessorupgrade" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "hdtv", "enigma2-plugin-systemplugins-videomode" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "dvbapi5", "usbtunerhelper" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "3dtv", "enigma2-plugin-systemplugins-osd3dsetup" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "wifi", "enigma2-plugin-systemplugins-wirelesslan", "", d)} \
				"

DEPENDS += "enigma2 enigma2-plugins enigma2-pliplugins"

ENIGMA2_OPTIONAL = " \
				enigma2-skins \
				enigma2-plugins \
				enigma2-pliplugins \
				task-openplugins \
				enigma2-plugin-extensions-tuxcom \
				enigma2-plugin-extensions-tuxterm \
				${@base_contains("MACHINE_FEATURES", "usbhost", "enigma2-plugin-drivers-usbserial" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "dvbapi5", "dvb-usb-drivers-meta" , "", d)} \
				enigma2-plugin-security-firewall \
				enigma2-plugin-extensions-xmltvimport \
				enigma2-plugin-extensions-ambx \
				enigma2-plugin-extensions-project-valerie \
				${@base_contains("MACHINE_FEATURES", "hdtv", \
					" \
					enigma2-plugin-skins-magic-hd \
					enigma2-plugin-skins-pli-hd \
					" , "", d)} \
				channelsettings-enigma2-meta \
				picons-enigma2-meta \
				softcams-enigma2-meta \
				dvbsnoop \
				mtd-utils \
				dvdfs \
				minidlna \
				nano \
				"

IMAGE_INSTALL += " \
				enigma2 \
				${ENIGMA2_PLUGINS} \
				aio-grab \
				tuxbox-common \
				libavahi-client \
				settings-autorestore \
				${@base_contains("MACHINE_FEATURES", "wifi", "${WIFI_DRIVERS}", "", d)} \
				${@base_contains("MACHINE_FEATURES", "dvbapi5", "${DVB_USB_DRIVERS}" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "dvbapi5", "${DVB_USB_FIRMWARE}" , "", d)} \
				"

OPTIONAL_PACKAGES += " \
			${ENIGMA2_OPTIONAL} \
			"

export IMAGE_BASENAME = "blackopen"


