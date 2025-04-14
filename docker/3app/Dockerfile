# Use an existing base image
FROM java17maven

# Set the working directory in the container
WORKDIR /demo

# Copy application files from host to container
COPY demo .

# Install dependencies
RUN mvnd package

# Set the default command to execute when the container starts
CMD ["java", "-jar", "./target/demo-0.0.1-SNAPSHOT.jar"]
