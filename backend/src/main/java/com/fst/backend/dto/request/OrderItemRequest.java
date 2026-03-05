package com.fst.backend.dto.request;

public class OrderItemRequest {

    private Long orderId;
    private Long productId;
    private int quantity;
    private double price;

    public OrderItemRequest() {}

    public OrderItemRequest(Long orderId, Long productId, int quantity, double price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getOrderId() {return orderId;}
    public void setOrderId(Long orderId) {this.orderId = orderId;}

    public Long getProductId() {return productId;}
    public void setProductId(Long productId) {this.productId = productId;}

    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
}
