DESCRIPTION = "CF mount tool"
SECTION = "base"
PRIORITY = "optional"
MAINTAINER = "Mike Looijmans <MiLo@pli-images.org>"

PV = "1.0"
PR = "r2"
DEPENDS = "klcc-cross"

SRC_URI = "file://boottool-${MACHINE}.c file://root_to_cf.sh"

S = "${WORKDIR}/"

do_install_append() {
	install -d ${D}/boot/bin
	install ${S}/boottool ${D}/boot/bin/initcf
	install -d ${D}/boot/dev
	install -d ${D}/usr/bin
	install -m 755 ${WORKDIR}/root_to_cf.sh ${D}/usr/bin/
}

do_compile_append() {
	${STAGING_BINDIR_CROSS}/klcc ${S}/boottool-${MACHINE}.c -o ${S}/boottool
}

pkg_postinst() {
	mknod -m 660 $D/boot/dev/hdc b 22 0
	mknod -m 660 $D/boot/dev/hdc1 b 22 1
	if [ ! -f $D/boot/bin/initflash ]
	then
		mv $D/boot/bin/init $D/boot/bin/initflash
	fi
	ln -f $D/boot/bin/initcf $D/boot/bin/init
}

pkg_prerm() {
	if [ -f /boot/bin/initflash ]
	then
		rm -f /boot/bin/init
		mv /boot/bin/initflash /boot/bin/init
	fi
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/boot/bin/initcf /boot/dev /usr/bin"
