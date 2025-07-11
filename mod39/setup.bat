@echo off
echo ===== Configurando Ambiente =====

rem Configurar Tomcat
set "TOMCAT_HOME=%CD%\bin\apache-tomcat-9.0.106"
set "PATH=%TOMCAT_HOME%\bin;%PATH%"

echo 1. Descompactando Tomcat...
cd bin
if not exist "apache-tomcat-9.0.106" (
    echo Descompactando apache-tomcat-9.0.106.zip...
    powershell -command "Expand-Archive -Path 'apache-tomcat-9.0.106-windows-x64.zip' -DestinationPath '.'"
)
cd ..

echo 2. Verificando Maven...
where mvn
if %ERRORLEVEL% NEQ 0 (
    echo Maven nao encontrado! Por favor, instale o Maven e adicione ao PATH
    echo Voce pode baixar o Maven em: https://maven.apache.org/download.cgi
    echo Depois de instalar, adicione a pasta bin do Maven ao PATH do sistema
    pause
    exit /b 1
)

echo 3. Verificando Java...
where java
if %ERRORLEVEL% NEQ 0 (
    echo Java nao encontrado! Por favor, instale o Java e adicione ao PATH
    echo Voce pode baixar o Java em: https://www.oracle.com/java/technologies/downloads/
    echo Depois de instalar, adicione a pasta bin do Java ao PATH do sistema
    pause
    exit /b 1
)

echo 4. Configurando permissoes do Tomcat...
icacls "bin\apache-tomcat-9.0.106\bin\*.bat" /grant Everyone:F

echo ===== Configuracao concluida! =====
echo Agora voce pode executar o deploy.bat para compilar e implantar a aplicacao
pause
