#!/bin/sh

/usr/bin/showiframe /usr/share/backdrop.mvi

cd /home/root
LD_PRELOAD=/usr/lib/libopen.so.0.0 /usr/bin/enigma2

# enigma2 exit codes:
#
# 0 - restart enigma
# 1 - halt
# 2 - reboot
#
# >128 signal

ret=$?
case $ret in
	1)
		/sbin/halt
		;;
	2)
		/sbin/reboot
		;;
	4)
		/sbin/rmmod lcd
		/usr/sbin/fpupgrade --upgrade 2>&1 | tee /home/root/fpupgrade.log
		sleep 1;
		/sbin/rmmod fp
		/sbin/modprobe fp
		/sbin/reboot
		;;
	42)
		opkg upgrade 2>&1 > /home/root/ipkgupgrade.log
		/sbin/reboot
		;;
	*)
		;;
esac
