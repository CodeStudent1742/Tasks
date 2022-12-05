call runcrud.bat
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo RUNCRUD has errors – breaking work
goto fail

:browser
start "C:\Program Files\Google\Chrome\Application\chrome.exe" http://localhost:8080/crud/v1/tasks
if %ERRORLEVEL% == "0" goto end
echo.
echo Chrome Browser has errors – breaking work
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.
