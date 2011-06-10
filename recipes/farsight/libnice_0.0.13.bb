DESCRIPTION = "Libnice is an implementation of the IETF's draft Interactice Connectivity Establishment standard (ICE)."
HOMEPAGE = "http://nice.freedesktop.org/wiki/"
SRC_URI = "http://nice.freedesktop.org/releases/libnice-${PV}.tar.gz"

LICENSE = "LGPL/MPL"
DEPENDS = "glib-2.0 gstreamer"

inherit autotools

FILES_${PN} += "${libdir}/gstreamer-0.10/*.so"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/*a"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug"

do_compile_append() {
        for i in $(find ${S} -name "*.pc") ; do
            sed -i -e s:${STAGING_DIR_TARGET}::g \
                   -e s:/${TARGET_SYS}::g \
                      $i
        done
}


SRC_URI[md5sum] = "e5b9f799a57cb939ea2658ec35253ab9"
SRC_URI[sha256sum] = "d8dd260c486a470a6052a5323920878a084e44a19df09b15728b85c9e3d6edf0"

