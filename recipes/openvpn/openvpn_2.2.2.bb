require openvpn.inc

PR = "${INC_PR}.0"
SRC_URI = "http://swupdate.openvpn.org/community/releases/${P}.tar.gz \
	   file://openvpn"

# I want openvpn to be able to read password from file (hrw)
EXTRA_OECONF += "--enable-password-save"
DEFAULT_PREFERENCE = "-1"

