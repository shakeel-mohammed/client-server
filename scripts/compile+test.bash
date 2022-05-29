#!/bin/bash

source "./compile.bash"

cp -a ../compiled/** ../testing/aarch64
cp -a ../config.properties ../testing/aarch64

cd ../testing/aarch64

./stage2-test-aarch64 "java Main" -o tt -n

rm *.class
rm *.properties
rm *.xml