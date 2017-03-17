@echo off
java -jar Application.jar
echo %ERRORLEVEL%
echo Ozhidaetsia: 0 - Vy`vod spravki

java -jar Application.jar -h
echo %ERRORLEVEL%
echo Ozhidaetsia: 0 - Vy`vod spravki

java -jar Application.jar -login XXX -pass XXX
echo %ERRORLEVEL%
echo Ozhidaetsia: 1 - neizvesny`i` pol`zovatel`

java -jar Application.jar -login jdoe -pass XXX
echo %ERRORLEVEL%
echo Ozhidaetsia: 2 - neizvestny`i` parol`

java -jar Application.jar -login jdoe -pass sup3rpaZZ
echo %ERRORLEVEL%
echo Ozhidaetsia: 0 - uspeshnaia avtorizatciia

java -jar Application.jar -login jdoe -pass sup3rpaZZ -role READ -res a
echo %ERRORLEVEL%
echo Ozhidaetsia: 0 - uspeshnaia autentifikatciia

java -jar Application.jar -login jdoe -pass sup3rpaZZ -role READ -res a.b
echo %ERRORLEVEL%
echo Ozhidaetsia: 0 - uspeshnaia autentifikatciia (dochernii` resurs)

java -jar Application.jar -login jdoe -pass sup3rpaZZ -role XXX -res a.b
echo %ERRORLEVEL%
echo Ozhidaetsia: 3 - neizvestnaia rol`

java -jar Application.jar -login jdoe -pass sup3rpaZZ -role READ -res XXX
echo %ERRORLEVEL%
echo Ozhidaetsia: 4 - net dostupa

java -jar Application.jar -login jdoe -pass sup3rpaZZ -role WRITE -res a
echo %ERRORLEVEL%
echo Ozhidaetsia: 4 - net dostupa (ne ta rol`)

java -jar Application.jar -login jdoe -pass sup3rpaZZ -role WRITE -res a.bc
echo %ERRORLEVEL%
echo Ozhidaetsia: 4 - net dostupa (ne tot resurs)

java -jar Application.jar -login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol 100
echo %ERRORLEVEL%
echo Ozhidaetsia: 0 - uspeshny`i` akkaunting

java -jar Application.jar -login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 01-01-2015 -de 2015-12-31 -vol 100
echo %ERRORLEVEL%
echo Ozhidaetsia: 5 - nevalidnaia aktivnost` (neverny`i` format daty`)

java -jar Application.jar -login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol XXX
echo %ERRORLEVEL%
echo Ozhidaetsia: 5 - nevalidnaia aktivnost` (neverny`i` format ob``ema)

java -jar Application.jar -login X -pass X -role READ -res X -ds 2015-01-01 -de 2015-12-31 -vol XXX
echo %ERRORLEVEL%
echo Ozhidaetsia: 1 - neizvestny`i` pol`zovatel`

java -jar Application.jar -login X -pass X -role READ -res X
echo %ERRORLEVEL%
echo Ozhidaetsia: 1 - neizvestny`i` pol`zovatel`

pause
