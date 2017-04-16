#!/bin/bash
mkdir bin #
javac -cp "./libs/*" -d "./bin/" src/*.java src/DAO/Connector.java #
echo "Classes create" 
jar cvmf manifest.mf Application.jar -C ./bin / ./libs/ ./res/db/migration /db/migration ./res/log4j2.xml /res/ #
echo "Jar good"
rm -rf bin #
