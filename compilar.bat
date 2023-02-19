git clone https://github.com/PabloCaiza/library_microservices_v1.git && cd ./library_microservices_v1 && cd ./app-book && gradlew libertyPackage && docker build -t ianyangmax/app-books:1.0.0 . && cd .. && cd ./app-author && docker build -t ianyangmax/app-authors:1.0.0 . && cd .. && docker push ianyangmax/app-books:1.0.0 && docker push ianyangmax/app-authors:1.0.0 && docker compose up

