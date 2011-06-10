DESCRIPTION = "libnl is a library for applications dealing with netlink sockets"
SECTION = "libs/network"
LICENSE = "LGPL"
HOMEPAGE = "http://www.infradead.org/~tgr/libnl/"

PR = "r4"

inherit autotools pkgconfig

CFLAGS += '-DVLAN_FLAG_REORDER_HDR=1'

SRC_URI = "\
  http://www.infradead.org/~tgr/libnl/files/libnl-${PV}.tar.gz \
  file://local-includes.patch \
  file://fix-includes.patch \
  file://respect-ldflags.patch \
  file://netlink-local-fix.patch \
  file://dont-link-libnl-from-sysroot.patch \
"


SRC_URI[md5sum] = "ae970ccd9144e132b68664f98e7ceeb1"
SRC_URI[sha256sum] = "35cea4cfb6cd8af0cafa0f34fff81def5a1f193b8b8384299b4b21883e22edc3"
