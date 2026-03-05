package com.fst.backend.service;

import com.fst.backend.dto.request.OrderRequest;
import com.fst.backend.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse updateOrder(Long id, OrderRequest orderRequest);
    OrderResponse getOrderById(Long id);
    void deleteOrder(Long id);
    List<OrderResponse> getAllOrders();
}
