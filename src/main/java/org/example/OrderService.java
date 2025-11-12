package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SushiService sushiService;

    public Order createOrder(Order order) {
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                if (item.getSushi() != null && item.getSushi().getId() != null) {
                    Sushi sushi = sushiService.getSushiById(item.getSushi().getId())
                            .orElseThrow(() -> new RuntimeException("Sushi not found: " + item.getSushi().getId()));
                    item.setSushi(sushi);
                    item.setOrder(order);
                }
            }
        }
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByPhone(String phone) {
        return orderRepository.findByCustomerPhone(phone);
    }

    public Order updateOrderStatus(Long orderId, Order.OrderStatus status) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(status);
            return orderRepository.save(order);
        }
        throw new RuntimeException("Order not found: " + orderId);
    }

    public Double calculateOrderTotal(Long orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            if (order.getItems() != null) {
                return order.getItems().stream()
                        .mapToDouble(OrderItem::getTotalPrice)
                        .sum();
            }
            return 0.0;
        }
        throw new RuntimeException("Order not found: " + orderId);
    }

    public Integer calculatePreparationTime(Long orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            if (order.getItems() != null && !order.getItems().isEmpty()) {
                return order.getItems().stream()
                        .mapToInt(item -> {
                            if (item.getSushi() != null) {
                                return item.getSushi().getPreparationTime() * item.getQuantity();
                            }
                            return 0;
                        })
                        .max()
                        .orElse(0);
            }
            return 0;
        }
        throw new RuntimeException("Order not found: " + orderId);
    }
}