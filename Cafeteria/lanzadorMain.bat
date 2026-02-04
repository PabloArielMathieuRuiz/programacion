@echo off
echo Compilando el proyecto...


javac -d bin src\cafeteriaAUnaCapa\Cafeteria.java

echo Ejecutando la aplicación...

java -cp bin cafeteriaAUnaCapa.Cafeteria

pause
