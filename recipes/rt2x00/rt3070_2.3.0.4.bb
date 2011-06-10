DESCRIPTION = "Ralink 3070"
HOMEPAGE = "http://eng.ralinktech.com.tw/support.php?s=2"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
PR = "r1"

inherit module

# Original URL needs a click-wrap license.
SRC_URI = "http://www.penguin.cz/~utx/hardware/Ralink_3071/DPO_RT3070_LinuxSTA_V${PV}_20100604.tar.bz2 \
	   file://makefile.patch \
           file://add_belkin_F6D4050v2.patch \
	 "

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR}"

S = "${WORKDIR}/DPO_RT3070_LinuxSTA_V${PV}_20100604"

# Source is in .tar.tar.bz2 format.
do_unpack() {
	mkdir -p ${WORKDIR}
	cd ${WORKDIR}
	bzip2 -dc %s ${DL_DIR}/DPO_RT3070_LinuxSTA_V${PV}_20100604.tar.bz2 | tar x --no-same-owner -O -f - | tar x --no-same-owner -f -
}

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
	install -m 0644 ${S}/os/linux/rt3070sta.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
	install -d ${D}/etc/Wireless/RT2870STA
	install -m 0644 ${S}/RT2870STA.dat ${D}/etc/Wireless/RT2870STA
	install -d ${D}/lib/firmware
	install -m 0644 ${S}/common/*.bin ${D}/lib/firmware/
}

PACKAGES =+ "${PN}-firmware"
FILES_${PN}-firmware = "/etc /lib/firmware"

RDEPENDS_${PN} = "${PN}-firmware"

SRC_URI[md5sum] = "bbfa7a278ae8fa0208499aee048ed1ed"
SRC_URI[sha256sum] = "df33f63f5396c85ab755bdb9d6c99d40ebfb30f50bd554a1fd6d7a3fb6095db4"
