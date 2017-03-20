#!/bin/bash
fullError=0 #
failedTests=0 #
succeedTests=0 #
function subTest { #
answer=$3
./RUN.sh "$1"
error=$?
if [[ $answer != $error ]] #
then #
echo "Test $2 is failed (1)" #
((failedTests++)) #
((fullError=1)) #
else #
echo "Test $2 is passed (0)" #
((succeedTests++)) #
fi #
} #
#
echo "Start test"
#
subTest " " 1 0 #
#
subTest "-h " 2 0 #
#
subTest "-login XXX -pass XXX " 3 1 #
#
subTest "-login jdoe -pass XXX " 4 2 #
#
subTest "-login jdoe -pass sup3rpaZZ " 5 0 #
#
subTest "-login jdoe -pass sup3rpaZZ -role READ -res a " 6 0 #
#
subTest "-login jdoe -pass sup3rpaZZ -role READ -res a.b " 7 0 #
#
subTest "-login jdoe -pass sup3rpaZZ -role XXX -res a.b " 8 3 #
#
subTest "-login jdoe -pass sup3rpaZZ -role READ -res XXX " 9 4 #
#
subTest "-login jdoe -pass sup3rpaZZ -role WRITE -res a " 10 4 #
#
subTest "-login jdoe -pass sup3rpaZZ -role WRITE -res a.bc " 11 4 #
#
subTest "-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol 100 " 12 0 #
#
subTest "-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 01-01-2015 -de 2015-12-31 -vol 100 " 13 5 #
#
subTest "-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol XXX " 14 5 #
#
subTest "-login X -pass X -role READ -res X -ds 2015-01-01 -de 2015-12-31 -vol XXX " 15 1 #
#
subTest "-login X -pass X -role READ -res X " 16 1 #
#
echo "Exit code $fullError, succeedTests = $succeedTests, failedTests = $failedTests" #
exit $fullError #