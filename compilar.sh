#!/bin/bash
git clone https://github.com/PabloCaiza/library_microservices_v1.git
cd library_microservices_v1
#book microservice
cd app-book
./gradlew libertyPackage
docker build -t pjcaiza/app-books:1.0.0
cd ..
#author microservice 
cd app-author
docker build -t pjcaiza/app-authors:1.0.0
cd ..
#push docker hub
docker push pjcaiza/app-books:1.0.0
docker push pjcaiza/app-authors:1.0.0
#run docker compose 
docker compose up




