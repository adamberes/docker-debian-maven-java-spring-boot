mkdir demo\src
xcopy ..\..\src .\demo\src /E /I /Y
copy ..\..\pom.xml .\demo
copy ..\..\mvnw .\demo

docker build -t java-app .
