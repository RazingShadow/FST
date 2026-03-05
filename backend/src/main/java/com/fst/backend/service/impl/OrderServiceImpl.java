package com.fst.backend.service.impl;

import com.fst.backend.dto.exception.CustomerNotFoundException;
import com.fst.backend.dto.exception.OrderNotFoundException;
import com.fst.backend.dto.mapper.OrderMapper;
import com.fst.backend.dto.request.OrderRequest;
import com.fst.backend.dto.response.OrderResponse;
import com.fst.backend.persistence.entity.CustomerEntity;
import com.fst.backend.persistence.entity.OrderEntity;
import com.fst.backend.persistence.repository.CustomerRepository;
import com.fst.backend.persistence.repository.OrderRepository;
import com.fst.backend.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        OrderEntity orderEntity = orderMapper.toEntity(orderRequest);
        orderRepository.save(orderEntity);
        return orderMapper.toResponse(orderEntity);
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {

        OrderEntity existing = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        applyUpdates(existing, orderRequest);

        return orderMapper.toResponse(orderRepository.save(existing));
    }

    private void applyUpdates(OrderEntity orderEntity, OrderRequest orderRequest) {

        Optional.ofNullable(orderRequest.getOrderItems())
                        .ifPresent(items -> orderEntity.setItems(orderMapper.toEntity(items)));

        Optional.of(orderRequest.getTotalPrice())
                .ifPresent(orderEntity::setTotalPrice);

        Optional.ofNullable(orderRequest.getCustomerId())
                .ifPresent(customerId -> {
                    CustomerEntity customer = customerRepository.findById(customerId)
                            .orElseThrow(() -> new CustomerNotFoundException(customerId));
                    orderEntity.setCustomer(customer);
                });

        Optional.ofNullable(orderRequest.getStatus())
                .ifPresent(orderEntity::setStatus);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toResponse)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException(id);
        }
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }
}
