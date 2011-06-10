DESCRIPTION = "Tasks for Enlightenment/X11"
PR = "r11"

inherit task

PACKAGES = "task-e-x11-core task-e-x11"
PROVIDES = "task-e-x11-core task-e-x11"

PREFERRED_PROVIDER_virtual/imlib2 = "imlib-x11"

#
# X
#
RDEPENDS_task-e-x11-core = "rxvt-unicode xstroke xtscal xrandr xmodmap xdpyinfo \
                            ttf-bitstream-vera gpe-bootsplash-theme-e"

#
# E-core
#
RDEPENDS_task-e-x11-core += "elementary-tests expedite e-wm"


RDEPENDS_task-e-x11  = "task-e-x11-core"
# entice eclair examine evidence e-modules e-utils elitaire"
