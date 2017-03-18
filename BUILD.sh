#!/bin/bash
mkdir bin #
javac -cp "./libs/*" -d "./bin" src/*.java #
echo "Classes create" 
jar cvmf manifest.mf Application.jar -C ./bin / #
echo "Jar good"
rm -rf bin #
