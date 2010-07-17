DESCRIPTION = "Tuxbox Commander"
MAINTAINER = "Sven Karschewski <seddi@i-have-a-dreambox.com>"
DEPENDS = "freetype"

PV = "1.13+svn${SRCPV}"
PR = "r2"

SRC_URI = "${PLISVNURL}/${PLISVNBRANCH}/external;module=tuxcom;proto=${PLISVNPROTO} \
	file://add_advanced_rc.diff"

S = "${WORKDIR}/tuxcom"

inherit autotools

do_install() {
	install -d ${D}/usr/plugins
	install -d ${D}/usr/share/fonts
	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/Tuxcom
	install -m 0755 ${S}/tuxcom ${D}/usr/plugins/tuxcom
	install -m 0644 ${S}/fonts/pakenham.ttf ${D}/usr/share/fonts/pakenham.ttf
	install -m 0644 ${S}/python/__init__.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/Tuxcom/__init__.py
	install -m 0644 ${S}/python/plugin.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/Tuxcom/plugin.py
}

FILES_${PN} = "/usr/plugins /usr/share/fonts /usr/lib/enigma2/python/Plugins/Extensions/Tuxcom"
