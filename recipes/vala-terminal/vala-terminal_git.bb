DESCRIPTION = "A lightweight Terminal Emulator based on libvte, written in Vala."
SECTION = "x11/applications"
DEPENDS = "vala-native vte"
SRCREV = "6cfb8bf8eb1a3812e39fac10a4810b6680fa066a"
PV = "1.1.1+gitr${SRCPV}"
PE = "1"
PR = "r1"

inherit autotools

SRC_URI = "${FREESMARTPHONE_GIT}/vala-terminal.git;protocol=git;branch=master"
S = "${WORKDIR}/git"

RDEPENDS_${PN} = "ttf-liberation-mono"
RREPLACES_${PN} = "openmoko-terminal2"
RPROVIDES_${PN} = "openmoko-terminal2"
