
IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

BOOTLOGO ?= "vuplus-bootlogo"

IMAGE_INSTALL = " \
				task-base \
				busybox-inetd \
				vsftpd \
				util-linux-ng-sfdisk \
				avahi-daemon \
				fakelocale \
				timezones-alternative \
				${BOOTLOGO} \
				tuxbox-links \
				cifs \
				samba \
				early-configure \
				e2fsprogs-mke2fs \
				e2fsprogs-e2fsck \
				nfs-utils \
				kernel-params \
				sdparm \
				mc \
				openvpn \
				blackpole-base \
				blackholesocker \
				blackpole-feed-configs \
				"

OPTIONAL_PACKAGES ?= ""
OPTIONAL_PACKAGES += " \
			gdb strace \
			procps \
			tcpdump \
			ntp \
			openssh \
			ctorrent nzbget \
			transmission \
			sabnzbd \
			wakelan \
			inadyn-mt \
			cups \
			vim joe \
			libdca \
			dvd+rw-tools dvdauthor cdrkit \
			smartmontools \
			hdparm \
			rsync \
			ntfs-3g \
			mpd \
			parted \
			"

IMAGE_LINGUAS = ""

inherit image

