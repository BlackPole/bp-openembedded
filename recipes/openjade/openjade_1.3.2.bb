SECTION = "unknown"
DEPENDS = "opensp"
RDEPENDS_${PN} = "sgml-common"
DESCRIPTION = "OpenJade is a suite of tools for validating, \
processing, and applying DSSSL (Document Style Semantics and \
Specification Language) stylesheets to SGML and XML documents."
LICENSE = "BSD"
PR = "r5"
SRC_URI = "${SOURCEFORGE_MIRROR}/openjade/openjade-${PV}.tar.gz \
	   file://configure.patch \
	   file://autoconf.patch \
	   file://makefile.patch \
          "
SRC_URI_append_virtclass-native = " file://oj-native-libosp-fix.patch"

inherit autotools

EXTRA_OECONF = "--enable-spincludedir=${STAGING_INCDIR}/OpenSP \
                --enable-splibdir=${STAGING_LIBDIR}"

acpaths = "-I ${S}/config"

# Trailing whitespace is important. Otherwise compiler arguments will be messed
# up, resulting in a fail in do_configure.
CFLAGS_prepend = "-I${S}/include "

do_configure_append () {
	cp config/configure.in .
}

do_install_append () {
       # Refer to http://www.linuxfromscratch.org/blfs/view/stable/pst/openjade.html
       # for details.
       install -m 0755 ${S}/jade/.libs/openjade ${D}${bindir}/openjade
       ln -sf openjade ${D}${bindir}/jade

       oe_libinstall -a -so -C style libostyle ${D}${libdir}
       oe_libinstall -a -so -C spgrove libospgrove ${D}${libdir}
       oe_libinstall -a -so -C grove libogrove ${D}${libdir}

       install -d ${D}${datadir}/sgml/openjade-${PV}
       install -m 644 dsssl/catalog ${D}${datadir}/sgml/openjade-${PV}
       install -m 644 dsssl/*.{dtd,dsl,sgm} ${D}${datadir}/sgml/openjade-${PV}

       # The catalog must live in the sysroot and it must be there for
       # install-catalog to do its thing.
       install -d ${datadir}/sgml/openjade-${PV}
       install -m 644 dsssl/catalog ${datadir}/sgml/openjade-${PV}/catalog
       install-catalog --add ${sysconfdir}/sgml/openjade-${PV}.cat \
           ${datadir}/sgml/openjade-${PV}/catalog

       install-catalog --add ${sysconfdir}/sgml/sgml-docbook.cat \
           ${sysconfdir}/sgml/openjade-${PV}.cat
}

BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "7df692e3186109cc00db6825b777201e"
SRC_URI[sha256sum] = "1d2d7996cc94f9b87d0c51cf0e028070ac177c4123ecbfd7ac1cb8d0b7d322d1"
