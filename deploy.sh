#!/bin/bash

cd /home/user/fitbit/fitbit-support-services
echo "Updating Git.."
git pull origin master
echo "Building App.."
./gradlew build -x test
echo "Building Docker Image.."
./gradlew buildDocker -x test
echo "Restarting Container.."
docker-compose up -d

