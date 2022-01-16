#!/bin/bash

VERSION='15.0.1'
FILE="firefox-$VERSION.tar.bz2"
ARCH=`uname -m`
RELEASES='ftp.mozilla.org/pub/mozilla.org/firefox/releases'
TO='/var/lib'
FILE_PATH="$TO/$FILE"
EXEC='firefox/firefox'
GREEN='\033[0;32m'
RED='\033[1;31m'
NO_COLOR='\033[1;0m'

export LANGUAGE='en-US'
export LC_ALL='en_US.UTF-8'
export LANG='en-US'

if [ -d "$TO/firefox" ]; then
  echo -e "${GREEN}Removing the current installed version of Firefox...${NO_COLOR}"
  sudo apt-get purge firefox firefox-globalmenu firefox-gnome-support
  sudo apt-get autoremove
fi

echo -e "${GREEN}Going to home${NO_COLOR}"
cd $HOME

if [ ! -e $FILE ]; then
  echo -e "${GREEN}Downloading the file ($RELEASES/$VERSION/linux-$ARCH/$LANG/$FILE)...${NO_COLOR}"
  wget "$RELEASES/$VERSION/linux-$ARCH/$LANG/$FILE"
fi

if [ ! -e $FILE ]; then
  echo -e "${RED}The download was failed!${NO_COLOR}"
  echo -e "${RED}Check if that version was released on site: http://$RELEASES ${NO_COLOR}"
  exit 1
fi

echo -e "${GREEN}Extracting the zip file${NO_COLOR}"

tar -jxf $FILE

echo -e "${GREEN}Moving the folder firefox to /var/lib${NO_COLOR}"

if [ -d "$TO/firefox" ]; then
  sudo rm -rf $TO/firefox
fi

sudo mv -f firefox $TO/firefox

echo -e "${GREEN}Applying the permissions${NO_COLOR}"

sudo chmod +x $TO/$EXEC

echo -e "${GREEN}Creating the /usr/lib/firefox symbolic link to /var/lib${NO_COLOR}"

cd /usr/lib

if [ -f 'firefox' ]; then
  sudo rm firefox
fi

sudo ln -s /var/lib/firefox firefox

echo -e "${GREEN}Downloading the firefox.sh launcher${NO_COLOR}"

cd $TO/firefox

wget "https://gist.github.com/wbotelhos/3899743/raw/e769802e9242999ab837e6620f093e110fb10f5e/firefox.sh"

chmod +x firefox.sh

echo -e "${GREEN}Creating the launcher link on /usr/bin${NO_COLOR}"

cd /usr/bin

if [ -f 'firefox' ]; then
  sudo rm firefox
fi

sudo ln -s $TO/firefox/firefox.sh firefox

exit 0