# multi-stage Dockerfile

## building stage
### add jdk (alpine is for smaller images)
FROM openjdk:8-jdk-alpine AS BUILD
### copy project
COPY ./ /build/ 
### compile project
RUN cd build && ./mvnw clean package

## runtime stage
### add jre (alpine is for smaller images)
FROM openjdk:8-jre-alpine
### copy war executable from building stage
COPY --from=BUILD /build/target/NaturalTheScattering-0.0.1-SNAPSHOT.war /app/app.war
### expose server's port
EXPOSE ${APP_PORT}
### start server
CMD ["java", "-jar", "/app/app.war"]
