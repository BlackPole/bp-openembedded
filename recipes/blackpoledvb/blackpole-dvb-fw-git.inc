LICENCE = "closed"

SRCREV = "4eb21647ad89816cdc0ad90edcb486fa09ef9a31"

SRC_URI = "git://github.com/BjornLee/linux-firmware.git;protocol=git"

S = "${WORKDIR}/git"

PACKAGES = "${PN}"

FILES_${PN} += "${base_libdir}/firmware"

PR = "r1"
