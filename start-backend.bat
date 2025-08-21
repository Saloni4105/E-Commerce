@echo off
echo Starting Backend Server...
cd /d "e:\Desktop\FullStack project"
javac BackendServer.java
if %errorlevel% equ 0 (
    echo Compilation successful, starting server...
    java BackendServer
) else (
    echo Compilation failed!
    pause
)