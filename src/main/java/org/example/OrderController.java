package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private SushiService sushiService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/health")
    public String health() {
        return "Sushi Delivery API is running!";
    }

    @GetMapping("/sushi")
    public List<Sushi> getAllSushi() {
        return sushiService.getAllSushi();
    }

    @GetMapping("/sushi/{id}")
    public ResponseEntity<Sushi> getSushiById(@PathVariable Long id) {
        Optional<Sushi> sushi = sushiService.getSushiById(id);
        return sushi.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            Order savedOrder = orderService.createOrder(order);
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/orders/phone/{phone}")
    public List<Order> getOrdersByPhone(@PathVariable String phone) {
        return orderService.getOrdersByPhone(phone);
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id,
                                                   @RequestParam Order.OrderStatus status) {
        try {
            Order updatedOrder = orderService.updateOrderStatus(id, status);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/orders/{id}/total")
    public ResponseEntity<Double> getOrderTotal(@PathVariable Long id) {
        try {
            Double total = orderService.calculateOrderTotal(id);
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/orders/{id}/preparation-time")
    public ResponseEntity<Integer> getPreparationTime(@PathVariable Long id) {
        try {
            Integer time = orderService.calculatePreparationTime(id);
            return ResponseEntity.ok(time);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}