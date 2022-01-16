#!/bin/bash
#title        : install_firefix_dev.sh
#description  : This script will install Firefox Developer Edition x86_64
#author       : Alexandre ZANNi
#date         : 2016/08/30
#version      : 0.1
#bash_version : GNU bash, version 4.2.47(1)-release (x86_64-suse-linux-gnu)
#=============================================================================

#
# Warning: Require sudo to move files into /opt/

# You can choose lang
lang="en-US"
url="https://download.mozilla.org/?product=firefox-aurora-latest-ssl&os=linux64&lang=$lang"

# Get filename
solved_URL=$(curl -L --head -w '%{url_effective}' $url 2>/dev/null | tail -n1)
filename_with_ext=$(echo $solved_URL | sed 's/.*\///')

# Download the file
wget $url -P $HOME --trust-server-names

# Remove archive extension (ex: bz2)
filename=$(echo $filename_with_ext | sed 's/\.[^.]*$//')
# Remove tar extension
filename=$(echo $filename | sed 's/\.[^.]*$//')

temp_folder="$HOME/$filename/"
sub_folder="firefox/"
absolut_filename="$HOME/$filename_with_ext"
if [ ! -d $temp_folder ]
then
  # Create a temporary folder, extract archive
  mkdir -p $temp_folder && tar xaf $absolut_filename -C $temp_folder
  rm -rf "/opt/firefox-dev/" # remove if updating and don't prompt if not existing
  mv -f "$temp_folder$sub_folder" "/opt/firefox-dev/"
  # Create a shortcut
  cat > ~/.local/share/applications/firefox-dev.desktop << EOF
[Desktop Entry]
Name=Firefox Developer
GenericName=Firefox Developer Edition
Exec=/opt/firefox-dev/firefox
Terminal=false
Icon=/opt/firefox-dev/browser/icons/mozicon128.png
Type=Application
Categories=Application;Network;X-Developer;
Comment=Firefox Developer Edition Web Browser
EOF
  # Remove the temporary folder & archive
  rmdir $temp_folder
  rm $absolut_filename
fi