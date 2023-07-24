## О проекте

Бэкенд-часть платформы по перепродаже вещей.\
\
Разработана согласно [техническому заданию](https://github.com/Virusec/diploma-avito/blob/master/openapi.yaml).\
\
Реализован следующий функционал:
* Авторизация и аутентификация пользователей
* Распределение ролей между пользователями: пользователь и администратор
* Под каждым объявлением пользователи могут оставлять комментарии 
* CRUD для объявлений на сайте: администратор может удалять или редактировать все объявления и комментарии, а пользователи — только свои
* Показывать и сохранять картинки объявлений, аватарки пользователей
## В проекте используются
* Java 11
* Maven
* Spring Boot
* Spring Data
* Spring Security
* GIT
* REST 
* Lombok
* PostgreSQL
* Liquibase
* JUnit
* Mockito

## [Схема таблиц БД](https://github.com/Virusec/diploma-avito/wiki/%D0%A1%D1%85%D0%B5%D0%BC%D0%B0-%D1%82%D0%B0%D0%B1%D0%BB%D0%B8%D1%86-%D0%91%D0%94)
## Инструкция по запуску
* Клонировать проект в среду разработки
* Скопировать данные из файла application.origin в application.properties и внести значения для datasource.url, datasource.username, datasource.password
* Запустить Docker образ (docker run --rm -p 3000:3000 ghcr.io/bizinmitya/front-react-avito:v1.16) или использовать Postman
* Запустить main метод в классе DiplomaAvitoApplication
* Логин/пароль предустановленного админа: superadmin@adm.ru/123456789 
## Над проектом работают
### "The Java Dream Team"
* [**Анатолий**](https://github.com/Virusec)
* [**Анна**](https://github.com/AnnaAskerova)
