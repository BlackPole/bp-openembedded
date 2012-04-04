MODULE = "FPGAUpgrade"
DESCRIPTION = "update your bouquets from dreambox"
HOMEPAGE = https://github.com/rossi2000/rossi2000-FPGAUpgrade

inherit gitpkgv
PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"
PR = "r0.1"

require rossi2000.inc

PLUGINPATH = "/usr/lib/enigma2/python/Plugins/SystemPlugins/${MODULE}"
do_install() {
	install -d ${D}${PLUGINPATH}
	cp -rp ${S}/plugin/* ${D}${PLUGINPATH}
}

FILES_${PN} = "${PLUGINPATH}"
