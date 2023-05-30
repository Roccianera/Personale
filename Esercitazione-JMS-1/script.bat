@echo off
setlocal

set "java_path=C:\Program Files\Eclipse Adoptium\jdk-17.0.6.10-hotspot\bin\java.exe"
set "class_path=C:\Users\hamza\OneDrive\ES_ACP_14_05_23\Personale\Esercitazione-JMS-1"
set "argfile_path=C:\Users\hamza\AppData\Local\Temp\cp_8kpejzuextwk83j65gi0k496g.argfile"
set "main_class=client.Client"

for /L %%i in (1,1,10) do (
    start "Processo %%i" "%java_path%" "@%argfile_path%" "%main_class%"
)

endlocal
