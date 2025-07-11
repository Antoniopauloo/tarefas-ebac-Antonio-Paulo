@echo off
echo ===== Instalando Maven =====

set MAVEN_VERSION=3.9.4
set MAVEN_URL=https://archive.apache.org/dist/maven/maven-3/%MAVEN_VERSION%/binaries/apache-maven-%MAVEN_VERSION%-bin.zip
set MAVEN_ZIP=%CD%\bin\apache-maven-%MAVEN_VERSION%-bin.zip
set MAVEN_DIR=%CD%\bin\apache-maven-%MAVEN_VERSION%

echo 1. Baixando Maven %MAVEN_VERSION%...
if not exist "bin" mkdir bin

echo Tentando download de %MAVEN_URL%
echo Para arquivo %MAVEN_ZIP%

powershell -Command ^
    try { ^
        $webClient = New-Object Net.WebClient; ^
        $webClient.DownloadFile('%MAVEN_URL%', '%MAVEN_ZIP%'); ^
        Write-Host 'Download concluido com sucesso!' ^
    } catch { ^
        Write-Host 'Erro no download:' $_.Exception.Message; ^
        exit 1 ^
    }

if not exist "%MAVEN_ZIP%" (
    echo Erro: Arquivo ZIP do Maven nao foi baixado!
    echo Por favor, baixe manualmente de:
    echo %MAVEN_URL%
    echo E coloque o arquivo em: %MAVEN_ZIP%
    pause
    exit /b 1
)

echo 2. Descompactando Maven...
if not exist "%MAVEN_DIR%" (
    powershell -command "Expand-Archive -Path '%MAVEN_ZIP%' -DestinationPath 'bin'"
)

echo 3. Configurando Maven...
set "M2_HOME=%MAVEN_DIR%"
set "PATH=%M2_HOME%\bin;%PATH%"

echo 4. Testando instalacao...
call mvn -version

if %ERRORLEVEL% EQU 0 (
    echo Maven instalado com sucesso!
    echo Execute 'env.bat' novamente para atualizar as variaveis de ambiente
) else (
    echo Erro ao instalar Maven
)

pause
