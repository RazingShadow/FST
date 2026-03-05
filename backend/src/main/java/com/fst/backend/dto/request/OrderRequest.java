package com.fst.backend.dto.request;

import com.fst.backend.persistence.entity.OrderStatus;

import java.util.List;

public class OrderRequest {

    private Long productId;
    private List<OrderItemRequest> orderItems;
    private double totalPrice;
    private Long customerId;
    private OrderStatus status;

    public OrderRequest() {}

    public OrderRequest(Long productId, List<OrderItemRequest> orderItems, double totalPrice, Long customerId, OrderStatus status) {
        this.productId = productId;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.status = status;
    }

    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {this.productId = productId;}

    public List<OrderItemRequest> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItemRequest> orderItems) {this.orderItems = orderItems;}

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {this.totalPrice = totalPrice;}

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {this.customerId = customerId;}

    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {this.status = status;}

}
