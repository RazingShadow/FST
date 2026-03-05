package com.fst.backend.dto.response;

import com.fst.backend.persistence.entity.OrderStatus;

import java.util.List;

public class OrderResponse {

    private Long id;
    private List<OrderItemResponse> orderItems;
    private Double totalPrice;
    private Long customerId;
    private OrderStatus status;

    public OrderResponse() {}

    public OrderResponse(Long id, List<OrderItemResponse> orderItems, Double totalPrice, Long customerId, OrderStatus status) {
        this.id = id;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItemResponse> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItemResponse> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
