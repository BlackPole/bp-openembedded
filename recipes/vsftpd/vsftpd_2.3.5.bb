DESCRIPTION = "Secure ftp daemon"
SECTION = "console/network"
LICENSE = "GPL"
PR = "r0"

SRC_URI = "http://sources.vuplus-community.net/vsftpd-${PV}.tar.gz \
           file://init \
           file://vsftpd.conf"

inherit update-rc.d

do_configure() {
        # Fix hardcoded /usr, /etc, /var mess.
        cat tunables.c|sed s:\"/usr:\"${prefix}:g|sed s:\"/var:\"${localstatedir}:g \
        |sed s:\"${prefix}/share/empty:\"${localstatedir}/share/empty:g |sed s:\"/etc:\"${sysconfdir}:g > tunables.c.new
        mv tunables.c.new tunables.c
}

do_configure_append_openpli() {
        # do not link against libcap
        sed -i '/^#define VSF_SYSDEP_HAVE_LIBCAP$/ d' sysdeputil.c
        sed -i '/libcap/ d' vsf_findlibs.sh
}

do_compile() {
        oe_runmake "LIBS=-lcrypt -L${STAGING_LIBDIR}"
}

do_install() {
        install -d ${D}${sbindir}
        install -d ${D}${mandir}/man8
        install -d ${D}${mandir}/man5
#        oe_runmake 'DESTDIR=${D}' install
	install -m 0755 ${WORKDIR}/${PN}-${PV}/vsftpd ${D}/usr/sbin/vsftpd
        install -d ${D}${sysconfdir}
        install -m 0755 ${WORKDIR}/vsftpd.conf ${D}${sysconfdir}/vsftpd.conf
        install -d ${D}${sysconfdir}/init.d/
        install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/vsftpd
}

do_install_append_openpli() {
	install -m 500 -d ${D}${localstatedir}/empty
}

pkg_postinst() {
        if [ "${D}" != "x" ]; then
                exit 1
        fi
        addgroup ftp
        adduser --system --home /var/lib/ftp --no-create-home --ingroup ftp --disabled-password -s /bin/false ftp
        mkdir -p ${localstatedir}/share/empty
}

pkg_postinst_openpli() {
}

INITSCRIPT_NAME = "vsftpd"

INITSCRIPT_PARAMS = "defaults"
