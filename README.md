# Pizza Store Microservices (pizza-store-mcs)

## Описание проекта
**Pizza Store Microservices** — это микросервисное приложение для заказа пиццы. Проект находится в стадии разработки.
Все запросы маршрутизируются через API Gateway, а микросервисы зарегистрированы в Eureka Service. В API Gateway реализован Circuit Breaker для повышения отказоустойчивости. За безопасность отвечает Keycloak.

## Стек технологий
- **Backend:** Java, Spring Boot, Maven
- **Frontend:** React (в разработке)
- **База данных:** PostgreSQL
- **Авторизация и аутентификация:** Keycloak
- **Сообщения и события:** Kafka
- **Оркестрация сервисов:** Eureka, API Gateway
- **Логирование и мониторинг:** ELK Stack (в планах)
- **Контейнеризация:** Docker, Docker Compose

## Архитектура микросервисов

- **auth-repo (user-service)** — проксирует запросы к Keycloak, предоставляет REST API для управления пользователями (регистрация, аутентификация, получение токенов доступа).
- **catalog-repo (catalog-service)** — предоставляет API для просмотра списка пицц и создания новых (создание доступно только для пользователей с ролью ADMIN).
- **order-repo (order-service)** — позволяет создавать заказы и отправляет уведомления о новых заказах в notification-service.
- **notification-repo (notification-service, заглушка)** — принимает уведомления о заказах. В планах — реализация WebSocket-соединения для уведомлений пользователей.
- **delivery-repo (delivery-service, заглушка)** — подписан на топик Kafka о создании заказов и симулирует процесс готовки и доставки путем изменения статуса заказа и отправки обновленных данных в Kafka.

## Запуск проекта локально

1. Клонируйте репозиторий:
   ```sh
   git clone https://github.com/your-username/pizza-store-mcs.git
   cd pizza-store-mcs
   ```
2. Перейдите в репозитории **auth-repo**, **catalog-repo**, **order-repo** и запустите контейнеры с базой данных, Keycloak и Kafka:
   ```sh
   docker-compose up -d
   ```
3. Запустите микросервисы вручную или через IDE.
4. API Gateway доступен по адресу: `http://localhost:8080`
5. Keycloak развернут на `http://localhost:8088`, создан `pizza-realm`.
6. Получите токен через `user-service`, чтобы обращаться к защищенным сервисам.

## Планы по разработке
- [ ] Реализация WebSocket в notification-service для уведомлений пользователей
- [ ] Завершение разработки frontend на React
- [ ] Улучшение логирования с использованием ELK Stack
- [ ] Доработка delivery-service с более реалистичной симуляцией доставки


---
Проект находится в активной разработке. Любые предложения и PR приветствуются! 🚀

