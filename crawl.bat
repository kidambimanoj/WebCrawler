ECHO OFF

echo "executing"
call mvn compile exec:java -Dexec.mainClass="com.manoj.ui.Driver"

PAUSE;
