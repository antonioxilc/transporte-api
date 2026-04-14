package com.grupoait.prueba.service.impl;

import com.grupoait.prueba.entity.Order;
import com.grupoait.prueba.entity.OrderStatus;
import com.grupoait.prueba.repository.OrderRepository;
import com.grupoait.prueba.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.CREATED);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateStatus(UUID orderId, OrderStatus newStatus) {

        Order order = getOrderById(orderId);

        validateStatusTransition(order.getStatus(), newStatus);

        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    private void validateStatusTransition(OrderStatus current, OrderStatus next) {

        if (current == OrderStatus.CREATED &&
                (next == OrderStatus.IN_TRANSIT || next == OrderStatus.CANCELLED)) {
            return;
        }

        if (current == OrderStatus.IN_TRANSIT &&
                next == OrderStatus.DELIVERED) {
            return;
        }

        throw new RuntimeException("Invalid status transition");
    }
}
