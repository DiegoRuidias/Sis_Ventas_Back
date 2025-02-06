# Etapa 1: Construcción de la aplicación utilizando Maven
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo pom.xml y los archivos de código fuente
COPY pom.xml .

# Descargar las dependencias del proyecto
RUN mvn clean dependency:go-offline

# Copiar el código fuente completo
COPY src ./src

# Empaquetar la aplicación y generar el .jar
RUN mvn clean package -DskipTests

# Etapa 2: Construcción del contenedor con la imagen base de OpenJDK 21
FROM eclipse-temurin:22-jre

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo .jar generado desde la etapa de construcción
COPY --from=build /app/target/ventas-0.0.1-SNAPSHOT.jar app/ventas.jar

# Exponer el puerto en el que la aplicación Spring Boot se ejecutará
EXPOSE 8090

# Definir el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app/ventas.jar"]