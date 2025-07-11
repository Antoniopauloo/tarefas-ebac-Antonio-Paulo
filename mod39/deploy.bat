@echo off
call env.bat
echo ===== Iniciando Deploy da Aplicacao =====

echo 1. Compilando o projeto com Maven...
call mvn clean package

echo 2. Verificando se o arquivo WAR foi gerado...
if not exist "target\modulo39.war" (
    echo Erro: Arquivo WAR nao foi gerado!
    pause
    exit /b 1
)

echo 3. Configurando caminho do Tomcat...
SET TOMCAT_PATH=%CD%\bin\apache-tomcat-9.0.106

if not exist "%TOMCAT_PATH%\webapps" (
    echo Erro: Pasta webapps nao encontrada! Execute setup.bat primeiro!
    pause
    exit /b 1
)

echo 4. Copiando arquivo WAR para o Tomcat...
copy "target\modulo39.war" "%TOMCAT_PATH%\webapps\"

echo 5. Iniciando o Tomcat...
cd %TOMCAT_PATH%\bin
call startup.bat

echo 6. Aguardando o deploy (30 segundos)...
timeout /t 30

echo 7. Abrindo o navegador...
start http://localhost:8080/modulo39

echo ===== Deploy Concluido! =====
echo A aplicacao deve estar disponivel em: http://localhost:8080/modulo39
echo Lembre-se de tirar os screenshots:
echo 1. Pagina inicial com o formulario
echo 2. Lista de contatos apos adicionar registros
echo 3. Console do Tomcat mostrando o deploy
echo.
pause
