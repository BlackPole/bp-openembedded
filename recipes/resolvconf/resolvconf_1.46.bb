DESCRIPTION = "Resolvconf is a framework for keeping track of the system's \
information about currently available nameservers. It sets \
itself up as the intermediary between programs that supply \
nameserver information and programs that need nameserver \
information."
SECTION = "console/network"
LICENSE = "GPL"
AUTHOR = "Thomas Hood"
HOMEPAGE = "http://packages.debian.org/resolvconf"
DEPENDS = "bash"
RDEPENDS_${PN} = "bash"
PR = "r0"

SRC_URI = "${DEBIAN_MIRROR}/main/r/resolvconf/resolvconf_${PV}.tar.gz"

do_compile () {
	:
}

do_install () {
	install -d ${D}${sysconfdir} ${D}${sbindir} ${D}${base_sbindir} ${D}${localstatedir}/run/resolvconf/interface
	install -d ${D}${mandir}/man8 ${D}${docdir}/${P}
	cp -pPR etc/* ${D}${sysconfdir}/
	install -m 0755 bin/resolvconf ${D}${base_sbindir}/
	install -m 0644 README ${D}${docdir}/${P}/
	install -m 0644 man/resolvconf.8 ${D}${mandir}/man8/
}

PACKAGE_ARCH = "all"


SRC_URI[md5sum] = "b276fc8834793f5c969ed7b57cb51660"
SRC_URI[sha256sum] = "2996caa40fbe2e2c5b2558e2333bd520558aa21250a6ecc7310ca3e6f231292b"
