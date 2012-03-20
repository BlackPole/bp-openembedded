require blackpole-image-base.bb

BP_DVB_USB_DRIVERS = " \
				blackpole-dvb-common-modules \
        			blackpole-dvb-frontends \
        			blackpole-dvb-usb-modules \
        			blackpole-dvb-siano \
        			blackpole-dvb-em28xx \
				blackpole-dvb-firmwares \
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
#				enigma2-plugin-systemplugins-osdpositionsetup \
				enigma2-plugin-extensions-dyndns \
				enigma2-plugin-systemplugins-hdmicec \
				${@base_contains("MACHINE_FEATURES", "nohotplug", "", "enigma2-plugin-systemplugins-hotplug", d)} \
				${@base_contains("MACHINE_FEATURES", "dvb-c", "enigma2-plugin-systemplugins-cablescan" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "frontprocessor", "enigma2-plugin-systemplugins-frontprocessorupgrade" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "hdtv", "enigma2-plugin-systemplugins-videomode" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "dvbapi5", "usbtunerhelper" , "", d)} \
#				${@base_contains("MACHINE_FEATURES", "3dtv", "enigma2-plugin-systemplugins-osd3dsetup" , "", d)} \
				${@base_contains("MACHINE_FEATURES", "wifi", "enigma2-plugin-systemplugins-wirelesslan", "", d)} \
				enigma2-plugin-extensions-vuplusevent \
  				enigma2-plugin-systemplugins-ui3dsetup \
  				enigma2-plugin-systemplugins-uipositionsetup \
				"

DEPENDS += "enigma2 enigma2-plugins enigma2-pliplugins"

ENIGMA2_OPTIONAL = " \
				enigma2-plugins \
				enigma2-pliplugins \
				enigma2-plugin-extensions-tuxcom \
				enigma2-plugin-extensions-tuxterm \
				${@base_contains("MACHINE_FEATURES", "usbhost", "enigma2-plugin-drivers-usbserial" , "", d)} \
#				${@base_contains("MACHINE_FEATURES", "dvbapi5", "dvb-usb-drivers-meta" , "", d)} \
				enigma2-plugin-security-firewall \
				enigma2-plugin-extensions-xmltvimport \
				enigma2-plugin-extensions-ambx \
				enigma2-plugin-extensions-project-valerie \
				channelsettings-enigma2-meta \
				picons-enigma2-meta \
				dvbsnoop \
				mtd-utils \
				dvdfs \
				minidlna \
				nano \
				pcsc-lite \
				"

IMAGE_INSTALL += " \
				enigma2 \
				${ENIGMA2_PLUGINS} \
				${ENIGMA2_MACHINE_PLUGINS} \
				aio-grab \
				tuxbox-common \
				libavahi-client \
				settings-autorestore \
				${@base_contains("MACHINE_FEATURES", "wifi", "${WIFI_DRIVERS}", "", d)} \
				${@base_contains("MACHINE_FEATURES", "dvbapi5", "${BP_DVB_USB_DRIVERS}" , "", d)} \
				"

OPTIONAL_PACKAGES += " \
			${ENIGMA2_OPTIONAL} \
			"

export IMAGE_BASENAME = "blackpole"


