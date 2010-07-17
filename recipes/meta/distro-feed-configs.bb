DESCRIPTION = "Configuration files for online package repositories aka feeds"
PR = "r1"

DISTRO_FEED_PREFIX ?= "remote"
DISTRO_FEED_URI ?= "http://my-distribution.example/remote-feed/"

do_compile() {
    mkdir -p ${S}/${sysconfdir}/opkg
    for feed in all ${TARGET_ARCH} ${PACKAGE_EXTRA_ARCHS} ${MACHINE_ARCH}; do
        echo "src/gz ${DISTRO_FEED_PREFIX}-${feed} ${DISTRO_FEED_URI}/${feed}" > ${S}/${sysconfdir}/opkg/${feed}-feed.conf
    done
}
do_install () {
        install -d ${D}${sysconfdir}/opkg
        install -m 0644 ${S}/${sysconfdir}/opkg/* ${D}${sysconfdir}/opkg/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

#def distro_feed_configs(d):
#    import bb
#    parchs = bb.data.getVar( "PACKAGE_EXTRA_ARCHS", d, 1 ).split()
#    march = bb.data.getVar( "MACHINE_ARCH", d, 1 ).split()
#    archs = [ "all" ] + parchs + march
#    confs = [ ( "${sysconfdir}/opkg/%s-feed.conf" % feed ) for feed in archs ]
#    return " ".join( confs )
#
#CONFFILES_${PN} += '${@distro_feed_configs(d)}'

CONFFILES_${PN} += '${@ " ".join( [ ( "${sysconfdir}/opkg/%s-feed.conf" % feed ) for feed in "all ${PACKAGE_EXTRA_ARCHS} ${MACHINE_ARCH}".split() ] ) }'
