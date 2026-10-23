@echo off
setlocal EnableExtensions EnableDelayedExpansion
cd /d "%~dp0"

title Validador HTML - PDA e JFlex

echo ==========================================
echo     VALIDADOR DE TAGS HTML COM PDA
echo ==========================================
echo.

rem Permite informar o arquivo diretamente: run.bat exemplo.html
if not "%~1"=="" (
    set "ARQUIVO=%~1"
    if not exist "!ARQUIVO!" (
        echo ERRO: Arquivo "!ARQUIVO!" nao encontrado.
        goto erro
    )
    goto executar
)

rem Monta o menu automaticamente com os arquivos de entrada.
set /a TOTAL=0
for %%F in (*.html *.txt) do (
    if exist "%%~fF" (
        set /a TOTAL+=1
        set "OPCAO[!TOTAL!]=%%~nxF"
        echo   !TOTAL! - %%~nxF
    )
)

if !TOTAL! EQU 0 (
    echo Nenhum arquivo .html ou .txt encontrado nesta pasta.
    goto erro
)

echo.
:escolher
set "ESCOLHA="
set /p "ESCOLHA=Escolha o numero do arquivo: "
if not defined ESCOLHA goto invalida

rem Verifica a opcao comparando-a aos numeros validos, sem executar entrada do usuario.
set "ARQUIVO="
for /l %%N in (1,1,!TOTAL!) do (
    if "!ESCOLHA!"=="%%N" set "ARQUIVO=!OPCAO[%%N]!"
)
if not defined ARQUIVO goto invalida
goto executar

:invalida
echo Opcao invalida. Digite um numero de 1 a !TOTAL!.
goto escolher

:executar
echo.
echo Arquivo selecionado: !ARQUIVO!
echo.

rem Procura o JFlex na pasta pai ou nesta pasta.
set "JFLEX_JAR=..\jflex-full-1.9.1.jar"
if not exist "!JFLEX_JAR!" set "JFLEX_JAR=jflex-full-1.9.1.jar"
if not exist "!JFLEX_JAR!" (
    echo ERRO: jflex-full-1.9.1.jar nao encontrado.
    goto erro
)

java -jar "!JFLEX_JAR!" HTML.flex
if errorlevel 1 goto erro

javac -encoding UTF-8 Token.java Yylex.java PDA.java Main.java
if errorlevel 1 goto erro

echo.
java Main "!ARQUIVO!"
if errorlevel 1 goto erro

echo.
exit /b 0

:erro
echo.
echo Falha ao selecionar, compilar ou executar o projeto.
exit /b 1
