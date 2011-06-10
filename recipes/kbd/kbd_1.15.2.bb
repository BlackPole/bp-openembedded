inherit autotools

BBCLASSEXTEND = "native"

SRC_URI="http://ftp.kernel.org/pub/linux/utils/kbd/kbd-1.15.2.tar.bz2"
SRC_URI[md5sum] = "e850eb91e4d3b94b194efe8e953204c5"
SRC_URI[sha256sum] = "b3602d191eef7a6a8317fc3cd231efa40a89ac235dce57a77cac825a2a21eba6"

PACKAGES += "${PN}-consolefonts ${PN}-keymaps ${PN}-unimaps ${PN}-consoletrans"

FILES_${PN}-consolefonts = "${datadir}/consolefonts"
FILES_${PN}-consoletrans = "${datadir}/consoletrans"
FILES_${PN}-keymaps = "${datadir}/keymaps"
FILES_${PN}-unimaps = "${datadir}/unimaps"
