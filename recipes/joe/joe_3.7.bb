SECTION = "console/utils"
DESCRIPTION = "Console text editor with good functionality, good choice for vi-haters."
HOMEPAGE = "http://joe-editor.sourceforge.net/"
LICENSE ="GPL"
RDEPENDS_${PN} = "ncurses-terminfo"

SRC_URI = "${SOURCEFORGE_MIRROR}/joe-editor/joe-${PV}.tar.gz"
PR = "r0"

inherit autotools

