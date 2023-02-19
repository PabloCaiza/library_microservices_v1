FROM arm64v8/eclipse-temurin:17
RUN mkdir /app
WORKDIR /app
COPY build/libs/app-book.jar ./app.jar
CMD ["java","-jar","app.jar"]