#!/bin/bash

source "./compile.bash"

cp -a ../compiled/** ../testing/x86
cp -a ../config.properties ../testing/x86

cd ../testing/x86

./stage2-test-x86 "java Main" -o tt -n

rm *.class
rm *.properties
rm *.xml