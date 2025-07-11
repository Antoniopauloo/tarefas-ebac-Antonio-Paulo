@echo off
echo ===== Configurando Variaveis de Ambiente =====

rem Configurar Java
if exist "C:\Program Files\Java\jdk-17" (
    set "JAVA_HOME=C:\Program Files\Java\jdk-17"
) else if exist "C:\Program Files\Java\jdk1.8.0_301" (
    set "JAVA_HOME=C:\Program Files\Java\jdk1.8.0_301"
)

if defined JAVA_HOME (
    set "PATH=%JAVA_HOME%\bin;%PATH%"
    echo Java encontrado em: %JAVA_HOME%
) else (
    echo Java nao encontrado!
)

rem Configurar Maven
set MAVEN_VERSION=3.9.4
if exist "%CD%\bin\apache-maven-%MAVEN_VERSION%" (
    set "M2_HOME=%CD%\bin\apache-maven-%MAVEN_VERSION%"
) else if exist "C:\Program Files\Apache\maven" (
    set "M2_HOME=C:\Program Files\Apache\maven"
) else if exist "C:\apache-maven" (
    set "M2_HOME=C:\apache-maven"
)

if defined M2_HOME (
    set "PATH=%M2_HOME%\bin;%PATH%"
    echo Maven encontrado em: %M2_HOME%
) else (
    echo Maven nao encontrado!
)

rem Configurar Tomcat
set "TOMCAT_HOME=%CD%\bin\apache-tomcat-9.0.106"
set "PATH=%TOMCAT_HOME%\bin;%PATH%"
set "CATALINA_HOME=%TOMCAT_HOME%"

echo.
echo Variaveis configuradas:
echo JAVA_HOME=%JAVA_HOME%
echo M2_HOME=%M2_HOME%
echo TOMCAT_HOME=%TOMCAT_HOME%
echo CATALINA_HOME=%CATALINA_HOME%
echo.
echo Para testar, tente os comandos:
echo java -version
echo mvn -version
echo catalina.bat version
echo.
