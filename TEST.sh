#!/bin/bash
fullError=0
echo "Start test"
./RUN.sh #
error=$? #
answer=0 #
if [[ $answer != $error ]] #
then #
echo "Test 1 is failed (1)" #
fullError=1 #
else #
echo "Test 1 is passed (0)" #
fi #
#
./RUN.sh "-h" #
error=$? #
answer=0 #
if [[ $answer != $error ]] #
then #
echo "Test 2 is failed (1)" #
fullError=1 #
else #
echo "Test 2 is passed (0)" #
fi #
#
./RUN.sh "-login XXX -pass XXX "
error=$?
answer=1
if [[ $answer != $error ]] #
then #
echo "Test 3 is failed (1)" #
fullError=1 #
else #
echo "Test 3 is passed (0)" #
fi #
#
./RUN.sh "-login jdoe -pass sup3rpaZZ "
error=$?
answer=2
if [[ $answer != $error ]] #
then #
echo "Test 4 is failed (1)" #
fullError=1 #
else #
echo "Test 4 is passed (0)" #
fi #
#
./RUN.sh "-login jdoe -pass sup3rpaZZ "
error=$?
answer=0
if [[ $answer != $error ]] #
then #
echo "Test 5 is failed (1)" #
fullError=1 #
else #
echo "Test 5 is passed (0)" #
fi #
#
./RUN.sh "-login jdoe -pass sup3rpaZZ -role READ -res a "
error=$?
answer=0
if [[ $answer != $error ]] #
then #
echo "Test 6 is failed (1)" #
fullError=1 #
else #
echo "Test 6 is passed (0)" #
fi #
#
./RUN.sh "-login jdoe -pass sup3rpaZZ -role READ -res a.b "
error=$?
answer=0
if [[ $answer != $error ]] #
then #
echo "Test 7 is failed (1)" #
fullError=1 #
else #
echo "Test 7 is passed (0)" #
fi #
#
./RUN.sh "-login jdoe -pass sup3rpaZZ -role XXX -res a.b "
error=$?
answer=3
if [[ $answer != $error ]] #
then #
echo "Test 8 is failed (1)" #
fullError=1 #
else #
echo "Test 8 is passed (0)" #
fi #
#
./RUN.sh "-login jdoe -pass sup3rpaZZ -role READ -res XXX "
error=$?
answer=4
if [[ $answer != $error ]] #
then #
echo "Test 9 is failed (1)" #
fullError=1 #
else #
echo "Test 9 is passed (0)" #
fi #
#
./RUN.sh "-login jdoe -pass sup3rpaZZ -role WRITE -res a "
error=$?
answer=4
if [[ $answer != $error ]] #
then #
echo "Test 10 is failed (1)" #
fullError=1 #
else #
echo "Test 10 is passed (0)" #
fi #
#
./RUN.sh "-login jdoe -pass sup3rpaZZ -role WRITE -res a.bc "
error=$?
answer=4
if [[ $answer != $error ]] #
then #
echo "Test 11 is failed (1)" #
fullError=1 #
else #
echo "Test 11 is passed (0)" #
fi #
#
./RUN.sh "-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol 100 "
error=$?
answer=0
if [[ $answer != $error ]] #
then #
echo "Test 12 is failed (1)" #
fullError=1 #
else #
echo "Test 12 is passed (0)" #
fi #
#
./RUN.sh "-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 01-01-2015 -de 2015-12-31 -vol 100 "
error=$?
answer=5
if [[ $answer != $error ]] #
then #
echo "Test 13 is failed (1)" #
fullError=1 #
else #
echo "Test 13 is passed (0)" #
fi #
#
./RUN.sh "-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol XXX "
error=$?
answer=5
if [[ $answer != $error ]] #
then #
echo "Test 14 is failed (1)" #
fullError=1 #
else #
echo "Test 14 is passed (0)" #
fi #
#
./RUN.sh "-login X -pass X -role READ -res X -ds 2015-01-01 -de 2015-12-31 -vol XXX "
error=$?
answer=1
if [[ $answer != $error ]] #
then #
echo "Test 15 is failed (1)" #
fullError=1 #
else #
echo "Test 15 is passed (0)" #
fi #
#
./RUN.sh "-login X -pass X -role READ -res X "
error=$?
answer=1
if [[ $answer != $error ]] #
then #
echo "Test 16 is failed (1)" #
fullError=1 #
else #
echo "Test 16 is passed (0)" #
fi #
#
echo "Final exit code $fullError" #
exit $fullError #
