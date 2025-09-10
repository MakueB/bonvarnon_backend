# Bonvarnon Backend

Backend-сервис для кофейни **Bonvarnon**, реализованный на **Kotlin + Ktor**.  
Приложение отвечает за управление заказами, пользователями и меню, предоставляя REST API для клиентских приложений (веб/мобильного).

## 🚀 Технологии

- **Kotlin**  
- **Ktor** (HTTP сервер и роутинг)  
- **Exposed** (ORM для работы с БД)  
- **PostgreSQL** (основная база данных)  
- **HikariCP** (connection pool)  
- **Kotlinx Serialization** (JSON-сериализация)  
- **Gradle** (сборка и зависимости)

## 📂 Структура проекта

- `Application.module` – конфигурация сервера и подключение модулей  
- `routes/` – API-роуты (пользователи, заказы, меню)  
- `models/` – сущности доменной логики  
- `services/` – бизнес-логика приложения  
- `db/` – миграции и работа с базой данных  

## ⚙️ Возможности API

- Управление пользователями (регистрация, авторизация, роли)  
- Создание и отслеживание заказов  
- Управление меню (позиции, категории)  
- Отчетность и статистика  

## 📦 Запуск проекта

### 1. Клонирование репозитория
```bash
git clone https://github.com/MakueB/bonvarnon_backend.git
cd bonvarnon_backend
```
### 2. Настройка окружения
```bash
Создайте файл `.env` со следующими параметрами:


DB_URL=jdbc:postgresql://localhost:5432/bonvarnon
DB_USER=your_user
DB_PASSWORD=your_password
PORT=8080
```
### 3. Запуск
```bash
./gradlew run
```
