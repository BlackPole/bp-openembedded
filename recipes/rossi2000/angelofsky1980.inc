DEPENDS += "enigma2"

SRC_URI = "git://github.com/angelofsky1980/rossi2000-${MODULE}.git;protocol=git"

S="${WORKDIR}/git"

SRCREV_pn-${PN} ?= "${AUTOREV}"
