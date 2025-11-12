package org.example;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sushi_id")
    private Sushi sushi;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double itemPrice;

    public OrderItem() {}

    public OrderItem(Sushi sushi, Integer quantity) {
        this.sushi = sushi;
        this.quantity = quantity;
        this.itemPrice = sushi.getPrice();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public Sushi getSushi() { return sushi; }
    public void setSushi(Sushi sushi) { this.sushi = sushi; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getItemPrice() { return itemPrice; }
    public void setItemPrice(Double itemPrice) { this.itemPrice = itemPrice; }

    // Добавьте этот метод - он используется в OrderService
    public Double getTotalPrice() {
        return itemPrice * quantity;
    }
}