package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Добро пожаловать в Sushi Delivery Service! 🍣\n\n" +
                "Доступные endpoints:\n" +
                "GET  /api/health - Проверка работы API\n" +
                "GET  /api/sushi - Получить все суши\n" +
                "GET  /api/sushi/{id} - Получить суши по ID\n" +
                "GET  /api/orders - Получить все заказы\n" +
                "POST /api/orders - Создать новый заказ\n" +
                "GET  /api/orders/{id} - Получить заказ по ID\n" +
                "GET  /api/orders/phone/{phone} - Получить заказы по телефону\n" +
                "PUT  /api/orders/{id}/status - Обновить статус заказа\n" +
                "GET  /api/orders/{id}/total - Получить сумму заказа\n" +
                "GET  /api/orders/{id}/preparation-time - Получить время приготовления";
    }
}