FROM openjdk:17-slim

# Install netcat and postgresql-client
RUN apt-get update && apt-get install -y netcat postgresql-client

# Set the working directory
WORKDIR /app

# Copy the application JAR and wait-for-db script
COPY target/grocery-store-project-docker.jar /app/grocery-store-project-docker.jar
COPY wait-for-db.sh /app/wait-for-db.sh

# Modify wait-for-db.sh line endings for compatibility
RUN sed -i 's/\r$//' /app/wait-for-db.sh

# Make wait-for-db.sh executable
RUN chmod +x /app/wait-for-db.sh

# Command to run the application
CMD ["/app/wait-for-db.sh"]
