set curDir=%~dp0
set RH="C:\Program Files (x86)\Resource Hacker\ResourceHacker.exe"
set sourceDir=%curDir%\src\main\resources\windows
set targetDir=%curDir%\src\main\resources\windows

cd %sourceDir%

%RH% -open ver.rc -save %targetDir%\ver.res -action compile
%RH% -open mainicon.rc -save %targetDir%\mainicon.res -action compile

cd %curDir%
