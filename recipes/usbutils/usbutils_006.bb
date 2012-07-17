DESCRIPTION = "Host side USB console utilities."
SECTION = "base"
DEPENDS += "libusb1"
LICENSE = "GPLv2"
PRIORITY = "optional"

PR = "r0"

SRC_URI = "http://mirror.anl.gov/pub/linux/utils/usb/usbutils/usbutils-${PV}.tar.gz \
	  "
inherit autotools

