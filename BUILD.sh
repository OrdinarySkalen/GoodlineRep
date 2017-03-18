#!/bin/bash
mkdir out2 #
javac -cp ".\libs\commons-cli-1.3.1.jar;.\libs\commons-codec-1.10.jar;.\libs\commons-lang3-3.5.jar;." -d "./out2" src/*.java #
echo "Classes create" 
jar cvmf manifest.mf Application.jar -C ./out2 / #
echo "Jar good"
rm -rf out2 #
