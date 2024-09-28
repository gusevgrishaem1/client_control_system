
# ОФД Менеджер

Клиент Менеджер — это приложение для управления клиентами и тих кассами, разработанное с использованием Spring Framework.

## Функциональные возможности

- Добавление, редактирование и удаление клиентов
- Добавление, редактирование и удаление касс
- Добавление, редактирование и удаление справочников офд
- Добавление, редактирование и удаление справочников сно
- Добавление, редактирование и удаление справочников моделей касс

## Технологии

Приложение построено на следующих технологиях:

- **Spring Boot** — основной фреймворк для разработки
- **Spring Data JPA** — для работы с базой данных
- **Hibernate** — для ORM
- **MySQL** — для хранения данных
- **Maven** — система сборки

## Запуск приложения

### Предварительные требования

- Java 17+
- Maven 3+
- MySQL

### Установка

1. Клонируйте репозиторий на свой компьютер:

   ```bash
   git clone https://github.com/yourusername/client-manager.git
   ```

2. Перейдите в директорию проекта:

   ```bash
   cd client-manager
   ```

3. Убедитесь, что MySQL запущен, и у вас есть пользователь с правами для базы данных `test`.

4. Обновите настройки подключения к базе данных в файле `application.properties` (если требуется):

   ```properties
   server.port=8080
   spring.jpa.hibernate.ddl-auto=update
   logging.level.org.springframework.web=INFO
   logging.level.org.springframework.web.servlet=INFO
   spring.datasource.url=jdbc:mysql://${MYSQL_HOST:mysql_db}:3306/test
   spring.datasource.username=test
   spring.datasource.password=test
   ```

5. Соберите проект с помощью Maven:

   ```bash
   mvn clean install
   ```

6. Запустите приложение:

   ```bash
   mvn spring-boot:run
   ```

### Настройки

Основные настройки приложения находятся в файле `src/main/resources/application.properties`.

### Пример данных для администрирования:

```properties
ofd.security.admin.username=test
ofd.security.admin.password=test
```

Приложение будет доступно по адресу [http://localhost:8080](http://localhost:8080).

## Контрибьютинг

Если хотите предложить улучшения или сообщить о проблемах, создайте pull request или issue в репозитории.

---

Так README будет соответствовать текущему состоянию проекта без использования Thymeleaf.