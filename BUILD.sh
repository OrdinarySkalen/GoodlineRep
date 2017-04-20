#!/bin/bash
mkdir bin #
cp -R -v ./res/* ./bin #
echo "Classes create" 
jar cvmf manifest.mf praclab1.Application.jar -C ./bin / ./libs/ #
echo "Jar good"
rm -rf bin #
