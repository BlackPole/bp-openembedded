require samba.inc
require samba-basic.inc
LICENSE = "GPLv3"

# 3.3.0 and newer will upgrade your tdb database to a
# new version that you can not downgrade to use with older
# releases. More testing will happen before this is removed
# should there be further issues. Appears to work though :)
DEFAULT_PREFERENCE = "-1"

SRC_URI += "file://configure-3.3.0.patch;patch=1 \
            file://config-h.patch;patch=1 \
            file://mtab.patch;patch=1 \
            file://tdbheaderfix.patch;patch=1 "

PR = "r0"

EXTRA_OECONF += "\
	SMB_BUILD_CC_NEGATIVE_ENUM_VALUES=yes \
	samba_cv_CC_NEGATIVE_ENUM_VALUES=yes \
	linux_getgrouplist_ok=no \
	samba_cv_HAVE_BROKEN_GETGROUPS=no \
	samba_cv_HAVE_FTRUNCATE_EXTEND=yes \
	samba_cv_have_setresuid=yes \
	samba_cv_have_setresgid=yes \
	samba_cv_HAVE_WRFILE_KEYTAB=yes \
	"

do_configure() {
	oe_runconf
}

do_compile () {
	base_do_compile
}

SRC_URI[src.md5sum] = "81dd2816b4f5d81e87f7bc7aaa877ac5"
SRC_URI[src.sha256sum] = "235db193f787229491e0008fa13de21a298144212e22212d70745b20be146f09"

