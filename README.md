# Spring-Boot App in Docker 
## Self generated Docker Images from Debian, Open-JDK-17 and Java-App


```mermaid
---
config:
  look: handDrawn
  theme: neutral
---
graph LR
  E[Docker Registry] -->A
  F{{Install Java,Maven}} -->B
  G{{Install App}} -->C
  A[Image:Debina4me] --> B[Image:java-maven] --> C[Image:Sping-Boot-App] 
  ```

## Download the Packages and the Checksum

Here the page to download the package and the checksum in "SHA256"

[Download Sha256 from Open-JDK-17](https://packages.debian.org/en/bookworm/amd64/openjdk-17-jdk/download)

Store the checksum in File:[openjdk-17-jdk_17.0.14+7-1~deb12u1_amd64.deb.sha256.txt](./docker/bin-sources/openjdk-17-jdk_17.0.14+7-1~deb12u1_amd64.deb.sha256.txt)

Check the checksum for the downloaded file in PowerShell as following

```
#Download
Invoke-WebRequest -Uri "http://ftp.de.debian.org/debian/pool/main/o/openjdk-17/openjdk-17-jdk_17.0.14+7-1~deb12u1_amd64.deb" -OutFile openjdk-17-jdk_17.0.14+7-1~deb12u1_amd64.deb
CertUtil -hashfile openjdk-17-jdk_17.0.14+7-1~deb12u1_amd64.deb SHA256
$hashCode="8cb06bdd2f39bdc4c14b9324d1087ed6921695f2d71f39751debebb14da14a22"
if ((Get-Content openjdk-17-jdk_17.0.14+7-1~deb12u1_amd64.deb.sha256.txt -Raw) -eq $hashCode) { echo Match } else { echo No match }
```
The result should be: "Match"

Here the Web-Page for Maven download:
[Download Maven-Linux-Daemon](https://maven.apache.org/download.cgi?.)

Store the checksum in File:[maven-mvnd-1.0.2-linux-amd64.sha512.txt](./docker/bin-sources/maven-mvnd-1.0.2-linux-amd64.sha512.txt)

Check the checksum for the downloaded file in PowerShell
```
CertUtil -hashfile maven-mvnd-1.0.2-linux-amd64.zip SHA512
$hashCode='c403f95b7018675f1ca8caed92b92c448e77c89bb0bbfb7ca0bfacee545639d5dec8d03ceb8262e7a30d9026a3504fe7f53bdef4cd229fa4f7fb8c024d25fd26'
if ((Get-Content maven-mvnd-1.0.2-linux-amd64.sha512.txt -Raw) -eq $hashCode) { echo Match } else { echo No match }
```
The result should be: "Match"

## Prepare the Docker Container

Tree Container will be generated locally in the following chained order:

- Generate from Debian Vers 12.10 a local image.
- Install OpenJDK Version 17 with a Maven-Daemon in the next image
- Deploy the Spring-Boot App in the next one.

Finally the Deploy App will be started. 

## Spring-Boot-App 
Rest-API generates a random number between 10 and 50.

- http://localhost:8080/api/random

## Detailes Steps to Execute.

The command are executed in Windows Cmd-Shell

```
cd docker\1debian
# create the Debian local image 
docker build -t debian4me .
# check the container debian4me
docker run debian4me

cd ..\2java-maven
copy /Y ".\..\bin-sources\maven-mvnd-1.0.2-linux-amd64.zip" .
copy /Y ".\..\bin-sources\openjdk-17-jdk_17.0.14+7-1~deb12u1_amd64.deb" .
powershell -Command "Expand-Archive -Path ".\maven-mvnd-1.0.2-linux-amd64.zip" -DestinationPath . -Force"

# create the Prerequisites image with Java and Maven 
docker build --no-cache -t java17maven .

# check the container java17maven
docker run java17maven

cd ..\3app
# copy the App in local directory and generate the App Container
mkdir demo\src
xcopy ..\..\src .\demo\src /E /I /Y
copy ..\..\pom.xml .\demo
copy ..\..\mvnw .\demo

# build the container and generate the executable jar file
docker build -t java-app .
# start the App in the Container;internal port 8080 external port 8090
docker run -p 8040:8090 java-app

# call in Internet Broser the endpoin :
http://localhost:8040/api/random

# the result should be with from you generated random number:
Random number: 24

```
