DESCRIPTION = "Bootlogo support"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

RDEPENDS_${PN} += "virtual/showiframe"

IMAGES_VERSION = "1"
BINARY_VERSION = "7"

PV = "${BINARY_VERSION}.${IMAGES_VERSION}"
PR = "r5"

KERNEL_VERSION = "${@base_contains('PREFERRED_VERSION_linux-${MACHINE}', '2.6.18', '2.6.18', '3.1.1', d)}"

SRC_URI = "file://bootlogo.mvi file://backdrop.mvi file://bootlogo_wait.mvi file://switchoff.mvi file://bootlogo.sh"
SRC_URI_append_vuuno = " file://splash_cfe_auto.bin"
SRC_URI_append_vuultimo = " file://splash_cfe_auto.bin"

S = "${WORKDIR}/"

INITSCRIPT_NAME = "bootlogo"
INITSCRIPT_PARAMS = "start 21 S ."

inherit update-rc.d

MVI = "bootlogo backdrop bootlogo_wait"

do_install() {
	install -d ${D}/boot
	install -d ${D}/usr/share
	for i in ${MVI}; do
		install -m 0755 ${S}/$i.mvi ${D}/usr/share/$i.mvi;
		ln -sf /usr/share/$i.mvi ${D}/boot/$i.mvi;
	done;
	install -d ${D}/${sysconfdir}/init.d
	install -m 0755 ${S}/bootlogo.sh ${D}/${sysconfdir}/init.d/bootlogo
}

do_install_vuuno() {
	install -d ${D}/boot
        install -d ${D}/usr/share
        for i in ${MVI}; do
                install -m 0755 ${S}/$i.mvi ${D}/usr/share/$i.mvi;
                ln -sf /usr/share/$i.mvi ${D}/boot/$i.mvi;
        done;
        install -m 0755 ${S}/splash_cfe_auto.bin ${D}/boot/splash_cfe_auto.bin
	install -d ${D}/${sysconfdir}/init.d
	install -m 0755 ${S}/bootlogo.sh ${D}/${sysconfdir}/init.d/bootlogo
}
do_install_vuultimo() {
        install -d ${D}/boot
        install -d ${D}/usr/share
        for i in ${MVI}; do
                install -m 0755 ${S}/$i.mvi ${D}/usr/share/$i.mvi;
                ln -sf /usr/share/$i.mvi ${D}/boot/$i.mvi;
        done;
        install -m 0755 ${S}/splash_cfe_auto.bin ${D}/boot/splash_cfe_auto.bin
	install -d ${D}/${sysconfdir}/init.d
	install -m 0755 ${S}/bootlogo.sh ${D}/${sysconfdir}/init.d/bootlogo
}

pkg_preinst() {
	if [ "${KERNEL_VERSION}" = "3.1.1" ]; then
		[ -d /proc/stb ] && mount -o rw,remount /boot
	fi
}

pkg_postinst() {
	if [ "${KERNEL_VERSION}" = "3.1.1" ]; then
		[ -d /proc/stb ] && mount -o ro,remount /boot
	fi
}

pkg_prerm() {
	if [ "${KERNEL_VERSION}" = "3.1.1" ]; then
		[ -d /proc/stb ] && mount -o rw,remount /boot
	fi
}

pkg_postrm() {
	if [ "${KERNEL_VERSION}" = "3.1.1" ]; then
		[ -d /proc/stb ] && mount -o ro,remount /boot
	fi
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/boot /usr/share /etc/init.d"
