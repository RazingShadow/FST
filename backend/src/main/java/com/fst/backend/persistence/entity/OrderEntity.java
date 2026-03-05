package com.fst.backend.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) // persist items with order, delete items if order is deleted
    private List<OrderItemEntity> items;

    @Column(nullable = false)
    private double totalPrice;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    public OrderEntity() {}

    public OrderEntity(List<OrderItemEntity> items, double totalPrice, CustomerEntity customer, OrderStatus status) {
        this.items = items;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<OrderItemEntity> getItems() { return items; }
    public void setItems(List<OrderItemEntity> items) { this.items = items; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public CustomerEntity getCustomer() { return customer; }
    public void setCustomer(CustomerEntity customer) { this.customer = customer; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
