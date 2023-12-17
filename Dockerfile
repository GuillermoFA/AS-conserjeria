
# Etapa de construcción
FROM gradle:8.3-jdk17 AS build
WORKDIR /usr/app
LABEL authors="Guillermo"

# Copia solo los archivos necesarios para la construcción
COPY build.gradle settings.gradle ./
COPY ./src/ ./src/

# Ejecuta el comando gradle para construir el JAR
RUN gradle build --no-daemon

# Etapa final de ejecución
FROM openjdk:17-jdk

# Copia el JAR desde la etapa de construcción a la ubicación deseada
COPY --from=build /usr/app/build/libs/*.jar /app.jar

# Define el comando de entrada predeterminado al ejecutar el contenedor
ENTRYPOINT ["java", "-jar", "/app.jar"]