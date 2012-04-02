DESCRIPTION = "Khavari motor settings from 15W to 105.5E"

inherit gitpkgv

PN = "enigma2-plugin-settings-khavari-motor"
PR = "r0"
SRCREV = "3f3e588e58c35a29df824003ad233c2448d808a1"

SRC_URI = "git://github.com/BlackPole/bp-settings-khavari.git;protocol=git"

S = "${WORKDIR}/git"

INHIBIT_PACKAGE_STRIP = "1"

do_install() {
	install -d ${D}/etc/enigma2
	install -m 0644 ${S}/* ${D}/etc/enigma2/
}

PACKAGE_ARCH = "all"
FILES_${PN} = "/"
