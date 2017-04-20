#!/bin/bash
mkdir bin #
javac -cp "./libs/*" -d "./bin/" src/praclab1/*.java src/praclab1/domain/*.java src/praclab1/dao/*.java #
cp -R -v ./res/* ./bin #
echo "Classes create" 
jar cvmf manifest.mf praclab1.Application.jar -C ./bin / ./libs/ #
echo "Jar good"
rm -rf bin #
