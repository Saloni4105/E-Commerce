@echo off
echo Compiling Java files...
cd /d "e:\Desktop\FullStack project\MyProject\Ecommerce\Ecommerce\src\main\java"
javac -cp ".;../../../target/classes" com/Project/Ecommerce/*.java com/Project/Ecommerce/*/*.java
echo Starting server...
cd /d "e:\Desktop\FullStack project\MyProject\Ecommerce\Ecommerce"
java -cp "src/main/java;src/main/resources" com.Project.Ecommerce.EcommerceApplication
pause