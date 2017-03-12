@echo off
java -jar PracLab1.jar
echo %ERRORLEVEL%
echo Ozhidaetsia: 0 - Vy`vod spravki

java -jar PracLab1.jar -h
echo %ERRORLEVEL%
echo Ozhidaetsia: 0 - Vy`vod spravki

java -jar PracLab1.jar -login XXX -password XXX
echo %ERRORLEVEL%
echo Ozhidaetsia: 1 - neizvesny`i` pol`zovatel`

java -jar PracLab1.jar -login jdoe -password XXX
echo %ERRORLEVEL%
echo Ozhidaetsia: 2 - neizvestny`i` parol`

java -jar PracLab1.jar -login jdoe -password sup3rpaZZ
echo %ERRORLEVEL%
echo Ozhidaetsia: 0 - uspeshnaia avtorizatciia

java -jar PracLab1.jar -login jdoe -password sup3rpaZZ -role READ -resource a
echo %ERRORLEVEL%
echo Ozhidaetsia: 0 - uspeshnaia autentifikatciia

java -jar PracLab1.jar -login jdoe -password sup3rpaZZ -role READ -resource a.b
echo %ERRORLEVEL%
echo Ozhidaetsia: 0 - uspeshnaia autentifikatciia (dochernii` resurs)

java -jar PracLab1.jar -login jdoe -password sup3rpaZZ -role XXX -resource a.b
echo %ERRORLEVEL%
echo Ozhidaetsia: 3 - neizvestnaia rol`

java -jar PracLab1.jar -login jdoe -password sup3rpaZZ -role READ -resource XXX
echo %ERRORLEVEL%
echo Ozhidaetsia: 4 - net dostupa

java -jar PracLab1.jar -login jdoe -password sup3rpaZZ -role WRITE -resource a
echo %ERRORLEVEL%
echo Ozhidaetsia: 4 - net dostupa (ne ta rol`)

java -jar PracLab1.jar -login jdoe -password sup3rpaZZ -role WRITE -resource a.bc
echo %ERRORLEVEL%
echo Ozhidaetsia: 4 - net dostupa (ne tot resurs)

java -jar PracLab1.jar -login jdoe -password sup3rpaZZ -role READ -resource a.b -dateStart 2015-01-01 -dateEnd 2015-12-31 -volume 100
echo %ERRORLEVEL%
echo Ozhidaetsia: 0 - uspeshny`i` akkaunting

java -jar PracLab1.jar -login jdoe -password sup3rpaZZ -role READ -resource a.b -dateStart 01-01-2015 -dateEnd 2015-12-31 -volume 100
echo %ERRORLEVEL%
echo Ozhidaetsia: 5 - nevalidnaia aktivnost` (neverny`i` format daty`)

java -jar PracLab1.jar -login jdoe -password sup3rpaZZ -role READ -resource a.b -dateStart 2015-01-01 -dateEnd 2015-12-31 -volume XXX
echo %ERRORLEVEL%
echo Ozhidaetsia: 5 - nevalidnaia aktivnost` (neverny`i` format ob``ema)

java -jar PracLab1.jar -login X -password X -role READ -resource X -dateStart 2015-01-01 -dateEnd 2015-12-31 -volume XXX
echo %ERRORLEVEL%
echo Ozhidaetsia: 1 - neizvestny`i` pol`zovatel`

java -jar PracLab1.jar -login X -password X -role READ -resource X
echo %ERRORLEVEL%
echo Ozhidaetsia: 1 - neizvestny`i` pol`zovatel`

pause
