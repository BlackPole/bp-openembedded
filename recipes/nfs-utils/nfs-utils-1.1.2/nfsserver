#!/bin/sh
#

set -e

LIBDIR=/lib/modules/`uname -r`
if [ ! -d $LIBDIR ]; then
   LIBDIR=/lib/modules/`ls /lib/modules | tail -n 1`
fi

# Read config
NFSSERVER_ON=1
NFS_CL_IP=192.168.0.5
NFSNETMASKE=255.255.0.0
DIRECTORY=/media/hdd
MOUNTD_PORT_ON=0
MOUNTD_PORT=2049
OPTIONS=rw,no_root_squash,async
# end

DESC="NFS kernel daemon"
LIB=/lib/modules/2.6.9/extra
RPCNFSDCOUNT=3


PORTMAP_BIN=/sbin/portmap
NFSD_BIN=/usr/sbin/rpc.nfsd
MOUNTD_BIN=/usr/sbin/rpc.mountd
EXPORTFS_BIN=/usr/sbin/exportfs



# Exit if required binaries are missing.
[ -x $NFSD_BIN ] || exit 0
[ -x $MOUNTD_BIN ] || exit 0
[ -x $EXPORTFS_BIN ] || exit 0
[ -x $PORTMAP_BIN ] || exit 0

if [ $NFSSERVER_ON -ne 0 ]; then
	if [ ! -d /var/lib ] ; then mkdir /var/lib; fi
	rm -R /var/lib/nfs 2> /dev/null || /bin/true
	ln -sf /tmp /var/lib/nfs
	touch /var/lib/nfs/rmtab
fi

# See how we were called.
case "$1" in
	start)
		if [ $NFSSERVER_ON -ne 0 ]; then
#portmap
			echo -n "Starting portmap daemon:"
			echo -n " portmap"
			start-stop-daemon --start --quiet --exec $PORTMAP_BIN
			echo "."

			if [ -f /var/run/portmap.upgrade-state ]; then
				echo -n "Restoring old RPC service information..."
				sleep 1 # needs a short pause or pmap_set won't work. :(
				pmap_set </var/run/portmap.upgrade-state
				rm -f /var/run/portmap.upgrade-state
				echo "done."
			fi
			
			sleep 1
#NFSSERVER
			$EXPORTFS_BIN -i -o $OPTIONS :/$DIRECTORY
			if ! grep -qs exportfs /proc/modules ; then
				insmod $LIBDIR/kernel/fs/exportfs/exportfs.ko
			fi
			if ! grep -qs nfsd /proc/modules ; then
				insmod $LIBDIR/kernel/fs/nfsd/nfsd.ko
			fi
	
			if ! grep -qs /proc/fs/nfs /proc/mounts ; then
				mount -t nfsd nfsd /proc/fs/nfsd
			fi	
	
			echo "Exporting directories for $DESC..."
			echo "done."
			echo "Starting $DESC:"
			echo " nfsd"
			start-stop-daemon --start --quiet --exec $NFSD_BIN -- $RPCNFSDCOUNT
			echo " mountd"
			
			if [ $MOUNTD_PORT_ON -ne 0 ]; then
				start-stop-daemon --start --quiet --exec $MOUNTD_BIN -p $MOUNTD_PORT
			else
				start-stop-daemon --start --quiet --exec $MOUNTD_BIN
			fi

			echo "."
		fi
	;;
	stop)
#PORTMAP
		echo -n "Stopping portmap daemon:"
		echo -n " portmap"
		start-stop-daemon --stop --quiet --exec $PORTMAP_BIN
        	echo "."
		sleep 1
#NFSSERVER
		echo "Stopping $DESC: mountd"
		start-stop-daemon --stop --quiet --name rpc.mountd --user 0
		sleep 1
		echo " nfsd"
		start-stop-daemon --stop --quiet --name rpc.nfsd --user 0 --signal 2
		echo "."

		echo "Unexporting directories for $DESC..."
		$EXPORTFS_BIN -au
		umount /proc/fs/nfsd
		rm -rf /tmp/etab
		rm -rf /tmp/rmtab
		rm -rf /tmp/xtab
		echo "done."
	;;
	reload | force-reload)
		echo "Re-exporting directories for $DESC..."
		$EXPORTFS_BIN -r
		echo "done."
	;;
	restart)
		pmap_dump >/var/run/portmap.state
		$0 stop
		sleep 1
        	$0 start
		if [ ! -f /var/run/portmap.upgrade-state ]; then
			sleep 1
			pmap_set </var/run/portmap.state
		fi
		rm -f /var/run/portmap.state
	;;
  	*)
		$0 start
		exit 0
#		echo "Usage: nfs_server_script.sh {start|stop|reload|force-reload|restart}"
#		exit 1
	;;
esac

exit 0
