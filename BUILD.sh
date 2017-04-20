#!/bin/bash
mkdir bin #
javac -cp "./libs/*" -d "./bin/" src/*.java src/DomainClasses/*.java src/DAO/*.java #
cp -R -v ./res/* ./bin #
echo "Classes create" 
jar cvmf manifest.mf Application.jar -C ./bin / ./libs/ #
echo "Jar good"
rm -rf bin #
