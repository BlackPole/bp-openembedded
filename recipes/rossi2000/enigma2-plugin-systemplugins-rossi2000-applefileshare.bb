MODULE = "AppleFileShare"
DESCRIPTION = "Apple File Share for Dreambox"
HOMEPAGE = https://github.com/rossi2000/rossi2000-AppleFileShare

inherit gitpkgv
PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"
PR = "r0.1"

require rossi2000.inc

# Just a quick hack to "compile" it
do_compile() {
	python -O -m compileall ${S}
}

PLUGINPATH = "/usr/lib/enigma2/python/Plugins/SystemPlugins/${MODULE}"
do_install() {
	install -d ${D}${PLUGINPATH}
	cp -rp ${S}/plugin/* ${D}${PLUGINPATH}
}

FILES_${PN} = "${PLUGINPATH}"