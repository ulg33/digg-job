# job

This project uses Quarkus and Vue

## Running the application in dev mode

You can run your application in dev mode that enables live coding both for Java and Vue using:
```shell script
./mvnw compile quarkus:dev
cd src/main/webapp; npm run watch
```

The application should be available at http://localhost:8080

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

To build a docker image, make sure to package the application first
```shell script
./mvnw package
docker build -f src/main/Docker/Dockerfile.jvm -t job .
```

To start the docker image
```shell script
docker run -i --rm -p 8080:8080 job
```