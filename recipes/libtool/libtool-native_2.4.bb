require libtool.inc
PR = "${INC_PR}.1"

DEFAULT_PREFERENCE = "-1"

DEPENDS = ""

SRC_URI_append = " file://prefix.patch \
                 "
SRC_URI[md5sum] = "b32b04148ecdd7344abc6fe8bd1bb021"
SRC_URI[sha256sum] = "13df57ab63a94e196c5d6e95d64e53262834fe780d5e82c28f177f9f71ddf62e"

inherit native
EXTRA_OECONF = " --with-libtool-sysroot=${STAGING_DIR_NATIVE}"
do_configure_prepend () {
        # Remove any existing libtool m4 since old stale versions would break
        # any upgrade
        rm -f ${STAGING_DATADIR}/aclocal/libtool.m4
        rm -f ${STAGING_DATADIR}/aclocal/lt*.m4
}

do_install () {
        autotools_do_install
        install -d ${D}${bindir}/
        install -m 0755 ${HOST_SYS}-libtool ${D}${bindir}/${HOST_SYS}-libtool
}
