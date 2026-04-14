package com.grupoait.prueba.repository;

import com.grupoait.prueba.entity.Order;
import com.grupoait.prueba.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByOriginContainingIgnoreCase(String origin);

    List<Order> findByDestinationContainingIgnoreCase(String destination);

    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
