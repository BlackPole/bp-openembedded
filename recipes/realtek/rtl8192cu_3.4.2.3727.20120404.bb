DESCRIPTION = "driver for Realtek USB wireless devices"
SECTION = "kernel/modules"
LICENSE = "GPLv2"

#RCONFLICTS_${PN} = "kernel-module-rtl8192cu"
#RREPLACES_${PN} = "kernel-module-rtl8192cu"

inherit module 

SRC_URI = " \
	http://sources.vuplus-community.net/rtl8188C_8192C_8192D_usb_linux_v3.4.2_3727.20120404.tar.gz \
	file://rtl8192cu_3.4.2.3727.20120404/makefile.patch \
	file://rtl8192cu/additional_productids.patch \
"	

S = "${WORKDIR}/rtl8188C_8192C_8192D_usb_linux_v3.4.2_3727.20120404"

MACHINE_KERNEL_PR_append = ".6"

do_compile () {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS CC LD CPP
        oe_runmake ${MODULE_MAKE_FLAGS}
}

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/rtlwifi/rtl8192cu
	install -m 0644 ${S}/8192cu.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/rtlwifi/rtl8192cu/rtl8192cu.ko
	install -d ${D}/etc/modutils
	echo rtl8192cu > ${D}/etc/modutils/rtl8192cu
}


MODULE_MAKE_FLAGS += " \
	CROSS_COMPILE=${TARGET_PREFIX} \
	KVER=${KERNEL_VERSION} \
	KSRC=${STAGING_KERNEL_DIR} \
	KDIR=${STAGING_KERNEL_DIR} \
"

pkg_postinst_append() {
if [ -n "$D" ]; then
	exit 1
else
	update-modules || true
fi
}

pkg_postrm_append() {
	update-modules || true
}
