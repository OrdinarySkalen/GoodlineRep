#!/bin/bash
#javac -cp "..\commons-cli-1.3.1-bin\commons-cli-1.3.1\commons-cli-1.3.1.jar;..\commons-codec-1.10\commons-codec-1.10.jar;..\commons-lang3-3.5\commons-lang3-3.5.jar;." Application.java
mkdir out2 #
javac -cp ".\libs\*" -d "./out2" src/*.java #
echo "Classes create" 
jar cvmf manifest.mf Application.jar -C ./out2 / #
echo "Jar good"
rm -rf out2 #
