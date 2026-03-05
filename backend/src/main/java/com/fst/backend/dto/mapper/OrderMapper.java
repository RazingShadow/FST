package com.fst.backend.dto.mapper;

import com.fst.backend.dto.exception.CustomerNotFoundException;
import com.fst.backend.dto.request.OrderItemRequest;
import com.fst.backend.dto.request.OrderRequest;
import com.fst.backend.dto.response.OrderItemResponse;
import com.fst.backend.dto.response.OrderResponse;
import com.fst.backend.persistence.entity.OrderEntity;
import com.fst.backend.persistence.entity.OrderItemEntity;
import com.fst.backend.persistence.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;
    private final CustomerRepository customerRepository;

    public OrderMapper(OrderItemMapper orderItemMapper, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.orderItemMapper = orderItemMapper;
    }

    public OrderResponse toResponse(OrderEntity orderEntity) {
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for(var orderItemEntity : orderEntity.getItems()) {
            orderItemResponses.add(orderItemMapper.toResponse(orderItemEntity));
        }
        return new OrderResponse(
                orderEntity.getId(),
                orderItemResponses,
                orderEntity.getTotalPrice(),
                orderEntity.getCustomer().getId(),
                orderEntity.getStatus()
        );
    }

    public OrderEntity toEntity(OrderRequest orderRequest) {
        var customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(orderRequest.getCustomerId()));

        return new OrderEntity(
                toEntity(orderRequest.getOrderItems()),
                orderRequest.getTotalPrice(),
                customer,
                orderRequest.getStatus()
        );
    }

    public List<OrderItemEntity> toEntity(List<OrderItemRequest> orderItemRequests) {
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        for(var orderItemRequest : orderItemRequests) {
            orderItemEntities.add(orderItemMapper.toEntity(orderItemRequest));
        }
        return orderItemEntities;
    }
}
