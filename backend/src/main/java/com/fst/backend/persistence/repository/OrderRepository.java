package com.fst.backend.persistence.repository;

import com.fst.backend.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
