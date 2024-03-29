FROM gradle:8.5-jdk21-alpine AS DEPS

WORKDIR /app
COPY . /app/

WORKDIR /app/ApiGateway
RUN gradle build -x test

WORKDIR /app/Service-Registry
RUN gradle build -x test


FROM openjdk:21-jdk-slim

WORKDIR /app
COPY --from=DEPS /app/ApiGateway/build/libs /app/build
COPY --from=DEPS /app/Service-Registry/build/libs /app/build
COPY --from=DEPS /app/command.sh /app/

EXPOSE 8590 8761

CMD ["bash","/app/command.sh"]