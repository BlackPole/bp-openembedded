require gstreamer.inc

EXTRA_OECONF += "ac_cv_func_register_printf_function=no"

do_configure_prepend() {
	sed -i -e s:docs::g Makefile.am
}

RREPLACES_gstreamer = "gst-plugin-queue2"
RCONFLICTS_gstreamer = "gst-plugin-queue2"
