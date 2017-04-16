#!/bin/bash
mkdir bin #
mkdir bin/pkg #
# javac -cp "./src/DAO/*" -s "./bin/pkg/" src/DAO/*.java # fail 
# javac -cp "./libs/*" -d "./bin" "src/DAO/*" -s "./bin/pkg/" src/*.java # fail
javac -cp "./libs/*" -d "./bin" src/*.java #
jar cvmf manifest.mf Application.jar -C ./bin / #
javac -cp "./libs/*","./Application.jar" -d "./bin/pkg/" src/DAO/*.java  # fail
# javac -cp "./libs/*" -d "./bin" src/*.java src/DAO/*.java # закидывает коннектор в корень
# javac -cp "./libs/*" -d "./bin" -s "src/DAO" src/*.java  
echo "Classes create" 
jar cvmf manifest.mf Application.jar -C ./bin / #
echo "Jar good"
rm -rf bin #
