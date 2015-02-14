#!/bin/sh

mkdir -p ./thumbs/440
mkdir -p ./thumbs/860

for x in ./*.png ; do

  convert ${x} -resize 440x ./thumbs/440/${x}
  convert ${x} -resize 860x ./thumbs/860/${x}

done
