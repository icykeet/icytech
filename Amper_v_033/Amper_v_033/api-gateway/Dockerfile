# Используем базовый образ OpenJDK 21
FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы сборки Gradle
COPY build.gradle.kts settings.gradle.kts /app/
COPY gradle /app/gradle
COPY gradlew /app/

# Копируем исходный код приложения
COPY src /app/src

# Делаем gradlew исполняемым и собираем проект, исключая тесты
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

# Копируем собранный jar файл
COPY build/libs/api-gateway-v033.jar /app/app.jar

# Запускаем приложение
CMD ["java", "-jar", "/app/app.jar"]

