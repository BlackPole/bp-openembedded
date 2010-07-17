DESCRIPTION = "Linux kernel for Dreambox DM8000"
LICENSE = "GPL"
PN = "linux-dm8000"
KV = "2.6.12"
PV = "2.6.12"
PR = "r9"

# note, the rX in the filename is *NOT* the packet revision - it's the patch revision.
SRC_URI += "${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/linux-${KV}.tar.bz2 \
	file://dm8000_defconfig \
	http://sources.dreamboxupdate.com/download/kernel-patches/linux-2.6.12-brcm-5.1.patch.bz2 \
	http://sources.dreamboxupdate.com/download/kernel-patches/linux-2.6.12-update_dvbapi-r1.patch.bz2 \
	http://sources.dreamboxupdate.com/download/kernel-patches/linux-2.6.12-dvb-multipid-r4.patch.bz2 \
	http://sources.dreamboxupdate.com/download/kernel-patches/linux-2.6.12-dvb-core-fix-several-locking-problems.patch.bz2 \
	http://sources.dreamboxupdate.com/download/kernel-patches/linux-2.6.12-dvbapi-pilot-rolloff-extension-r0.patch.bz2 \
	http://sources.dreamboxupdate.com/download/kernel-patches/linux-2.6.12-update-wireless.patch.bz2 \
	http://sources.dreamboxupdate.com/download/kernel-patches/linux-2.6.12-add-ioprio.patch.bz2 \
	file://linux-2.6.12-dvbapi-support-more-demux.patch \
	file://linux-2.6.12-dream-misc.patch \
	file://linux-2.6.12-dm8000-nand.patch \
	file://linux-2.6.12-dream-temp.patch \
	file://linux-2.6.12-brcm-mtd-blkdevfs-fix.diff \
	file://linux-2.6.12-brcm-fix-usb-for-revb0.diff \
	file://linux-2.6.12-set-custom-extraversion.patch \
	file://linux-2.6.12-fixup-prom-args.patch \
	file://linux-2.6.12-7400AB-enable-llsc.patch \
	file://linuxmips-2.6.12-fix-fadvise.patch \
	file://linuxmips-2.6.12-fix-futex.patch \
	file://linuxmips-2.6.12-gcc4-compile-fix.patch \
	file://linuxmips-2.6.12-gdb-fix.patch \
	file://linux-2.6.12-brcm-fix-minipci.patch \
	file://linux-2.6.12-fixup-memsize.patch \
	http://trappist.elis.ugent.be/~mronsse/cdfs/download/cdfs-2.6.12.tar.bz2 \
	file://linux-2.6-trailing-whitespaces-in-params.patch \
	file://linuxmips-2.6.12-gcc433-compile-fix.patch"

S = "${WORKDIR}/stblinux-2.6.12"

inherit kernel

FILES_kernel-image = "/boot/vmlinux.gz /boot/autoexec.bat"

export OS = "Linux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_OUTPUT = "vmlinux"
KERNEL_OBJECT_SUFFIX = "ko"

do_munge() {
	mv ${WORKDIR}/linux-2.6.12 ${WORKDIR}/stblinux-2.6.12
	if [ -d ${S}/drivers/sound ]; then
		rm -R ${S}/drivers/sound;
	fi;
}

addtask munge before do_patch after do_unpack

do_configure_prepend() {
	oe_machinstall -m 0644 ${WORKDIR}/dm8000_defconfig ${S}/.config
	if [ -d ${WORKDIR}/cdfs-${PV} ]; then
		mv ${WORKDIR}/cdfs-${PV} ${S}/fs/cdfs
		cd ${S} & patch -p0 < ${S}/fs/cdfs/patch.cdfs
	fi;
	oe_runmake oldconfig
}

do_install_append () {
	install -d ${D}/boot
	install -m 0755 vmlinux ${D}/boot/vmlinux
	echo "/flash/bootlogo.elf" > ${D}/boot/autoexec.bat
	gzip ${D}/boot/vmlinux
	echo "/flash/vmlinux.gz" >> ${D}/boot/autoexec.bat
}

pkg_preinst_kernel-image () {
	[ -d /proc/stb ] && mount -o rw,remount /boot
	true
}

pkg_postinst_kernel-image () {
	[ -d /proc/stb ] && mount -o ro,remount /boot
	true
}

pkg_prerm_kernel-image () {
	[ -d /proc/stb ] && mount -o rw,remount /boot
	true
}

pkg_postrm_kernel-image () {
	[ -d /proc/stb ] && mount -o ro,remount /boot
	true
}
