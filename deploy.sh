#!/bin/bash
echo "Updating Git.."
git pull origin master
echo "Building App.."
./gradlew build
echo "Building Docker Image.."
./gradlew buildDocker
echo "Restarting Container.."
docker-compose up -d

