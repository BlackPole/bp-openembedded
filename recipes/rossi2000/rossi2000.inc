DEPENDS += "enigma2"

SRC_URI = "git://github.com/rossi2000/rossi2000-${MODULE}.git;protocol=git"

S="${WORKDIR}/git"

SRCREV_pn-${PN} ?= "${AUTOREV}"