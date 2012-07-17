DESCRIPTION = "library to provide userspace access to USB devices"
HOMEPAGE = "http://libusb.sf.net"
SECTION = "libs"
LICENSE = "LGPLv2.1"

PR = "r0"

SRC_URI = " \
	${SOURCEFORGE_MIRROR}/libusb/libusb-${PV}.tar.bz2;name=tar \
	"
S = "${WORKDIR}/libusb-${PV}"

inherit autotools

EXTRA_OECONF = "--disable-build-docs"

SRC_URI[tar.md5sum] = "7f5a02375ad960d4e33a6dae7d63cfcb"
SRC_URI[tar.sha256sum] = "e920eedc2d06b09606611c99ec7304413c6784cba6e33928e78243d323195f9b"

