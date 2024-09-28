# Этап сборки
FROM maven:3.8.5-openjdk-17 AS build

# Устанавливаем рабочую директорию для сборки
WORKDIR /app

# Копируем pom.xml и другие необходимые файлы для разрешения зависимостей
COPY pom.xml ./

# Скачиваем зависимости, чтобы они кешировались при повторных сборках
RUN mvn dependency:go-offline -B

# Копируем исходный код проекта
COPY src ./src

# Собираем проект
RUN mvn clean package -DskipTests

# Этап выполнения
FROM openjdk:17-jdk-slim

# Создаём рабочую директорию
WORKDIR /app

# Копируем собранный JAR файл из предыдущего этапа
COPY --from=build /app/target/*.jar /app/app.jar

# Открываем порт (например, 8080 для Spring Boot)
EXPOSE 8080

# Запускаем приложение
CMD ["sh", "-c", "java -jar /app/app.jar"]
