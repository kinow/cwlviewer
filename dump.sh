#!/bin/sh
export TZ=C 
#set -e

BASE=$1
if [ -z "$BASE" ]; then
	echo "dump.sh BASE [DIR]"
	echo ""
	echo "Example:"
	echo "  dump.sh https://view.commonwl.org/ /var/backups/"
	exit 1;
fi

DIR=$2
if [ -z "$DIR" ]; then
	DIR=`pwd`
fi

URL="${BASE}/workflows.json?page=0&size=100000000"
FILE=${DIR}/`date --iso-8601=seconds | sed s/://g`.json
curl --silent --show-error --output $FILE $URL 
gzip $FILE
echo $FILE
