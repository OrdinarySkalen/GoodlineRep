java Application
echo %ERRORLEVEL%
java Application -login grom -pass 123qwe
echo %ERRORLEVEL%
java Application -login groza -pass ewq321
echo %ERRORLEVEL%
java Application -login groza -pass 321
echo %ERRORLEVEL%
java Application -login groze -pass ewq321
echo %ERRORLEVEL%
java Application -login grom -pass 123qwe -res A.B
echo %ERRORLEVEL%
java Application -login grom -pass 123qwe -res A.B role READ
echo %ERRORLEVEL%
java Application -login grom -pass 123qwe -res B.B role READ
echo %ERRORLEVEL%
java Application -login groza -pass ewq321 -res A.B role READ
echo %ERRORLEVEL%
