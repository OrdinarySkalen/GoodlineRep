#!/bin/bash
mkdir out2 #
javac -cp ".\libs\*" -d "./out2" src/*.java #
echo "Classes create" 
jar cvmf manifest.mf Application.jar -C ./out2 / #
echo "Jar good"
rm -rf out2 #
