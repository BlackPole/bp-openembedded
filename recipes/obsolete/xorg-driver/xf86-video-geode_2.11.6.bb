require xorg-driver-video.inc
DESCRIPTION = "X.org server -- Geode GX2/LX display driver"
PE = "1"
PR = "${INC_PR}.0"

SRC_URI[archive.md5sum] = "3ff0dfb4b3bd8872c6efb70371cb6b98"
SRC_URI[archive.sha256sum] = "4a1ada47bc08db13a57f32e73cbc5a5f0774f87ba447071737e50dfefb741f91"

COMPATIBLE_HOST = "i.86.*-linux"
