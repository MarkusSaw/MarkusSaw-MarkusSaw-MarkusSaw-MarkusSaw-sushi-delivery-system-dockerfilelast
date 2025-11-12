Инструкция по запуску проекта

Скачивание и запуск

# 1. Клонировать репозиторий
"git clone https://github.com/MarkusSaw/MarkusSaw-MarkusSaw-MarkusSaw-MarkusSaw-sushi-delivery-system-dockerfilelast.git"

# 2. Перейти в папку проекта
cd MarkusSaw-MarkusSaw-MarkusSaw-MarkusSaw-sushi-delivery-system-dockerfilelast

# 3. Запуск одной командой
docker-compose up --build



# Остановка контейнеров
docker-compose down

# Остановка с удалением данных БД
docker-compose down -v




API Endpoints (порт 8080)



# Главная страница с инструкцией
http://localhost:8080/


# Проверка здоровья
http://localhost:8080/api/health

# Получить все суши
http://localhost:8080/api/sushi

# Получить суши по ID (1-5)
http://localhost:8080/api/sushi/1

# Получить все заказы
http://localhost:8080/api/orders

# Получить заказ по ID
http://localhost:8080/api/orders/1

# Получить заказы по номеру телефона
http://localhost:8080/api/orders/phone/%2B79161234567

# Получить сумму заказа
http://localhost:8080/api/orders/1/total

# Получить время приготовления заказа
http://localhost:8080/api/orders/1/preparation-time



# Создать новый заказ
 -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerName": "Иван Иванов",
    "customerPhone": "+79161234567",
    "deliveryAddress": "ул. Примерная, д. 123",
    "items": [
      {
        "sushi": {"id": 1},
        "quantity": 2
      },
      {
        "sushi": {"id": 2},
        "quantity": 1
      }
    ]
  }'



# Обновить статус заказа
 -X PUT "http://localhost:8080/api/orders/1/status?status=PREPARING"
