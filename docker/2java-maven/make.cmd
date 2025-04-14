copy /Y ".\..\bin-sources\maven-mvnd-1.0.2-linux-amd64.zip" .
copy /Y ".\..\bin-sources\openjdk-17-jdk_17.0.14+7-1~deb12u1_amd64.deb" .
powershell -Command "Expand-Archive -Path ".\maven-mvnd-1.0.2-linux-amd64.zip" -DestinationPath . -Force"

docker build --no-cache -t java17maven .

