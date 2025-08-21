@echo off
cd /d "e:\Desktop\FullStack project\MyProject\Ecommerce\Ecommerce"
java -cp "target/classes;%USERPROFILE%\.m2\repository\*\*" com.Project.Ecommerce.EcommerceApplication
pause