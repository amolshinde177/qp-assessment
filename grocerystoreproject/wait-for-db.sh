#!/bin/bash

# Wait for the database to be available
until nc -z -v -w30 dbservice 5432
do
  echo "Waiting for database connection..."
  sleep 1
done

echo "Postgres is up - executing command"
exec java -jar /app/grocery-store-project-docker.jar
