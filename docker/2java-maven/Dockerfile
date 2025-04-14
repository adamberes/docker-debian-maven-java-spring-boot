# Use an existing base image
FROM debian4me

# Set the working directory in the container
WORKDIR /app

# Copy application files from host to container
COPY ./maven-mvnd-1.0.2-linux-amd64 ./../opt/maven-mvnd-1.0.2-linux-amd64
COPY ./openjdk-17-jdk_17.0.14+7-1~deb12u1_amd64.deb .

# Install openjdk
RUN apt-get install -y ./openjdk-17-jdk_17.0.14+7-1~deb12u1_amd64.deb && rm ./openjdk-17-jdk_17.0.14+7-1~deb12u1_amd64.deb

ENV PATH="/opt/maven-mvnd-1.0.2-linux-amd64/bin:${PATH}"
ENV JAVA_HOME="/usr/lib/jvm/java-17-openjdk-amd64"
ENV MAVEN_HOME="/opt/maven-mvnd-1.0.2-linux-amd64"

# Check the instelled java version
CMD ["java", "--version"]
