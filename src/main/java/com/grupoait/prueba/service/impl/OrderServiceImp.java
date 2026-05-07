package com.grupoait.prueba.service.impl;

import com.grupoait.prueba.dto.order.OrderRequestDTO;
import com.grupoait.prueba.dto.order.OrderResponseDTO;
import com.grupoait.prueba.entity.Order;
import com.grupoait.prueba.entity.OrderStatus;
import com.grupoait.prueba.exception.ResourceNotFoundException;
import com.grupoait.prueba.mapper.OrderMapper;
import com.grupoait.prueba.repository.OrderRepository;
import com.grupoait.prueba.service.OrderService;
import lombok.RequiredArgsConstructor;
import com.grupoait.prueba.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        Order order = orderMapper.toEntity(request);
        order.setStatus(OrderStatus.CREATED);

        Order saved = orderRepository.save(order);
        return orderMapper.toDTO(saved);
    }

    @Override
    public OrderResponseDTO getOrderById(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        return orderMapper.toDTO(order);
    }

    @Override
    public List<OrderResponseDTO> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    @Override
    public OrderResponseDTO updateStatus(UUID orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        OrderStatus newStatus = OrderStatus.valueOf(status);
        validateStatusTransition(order.getStatus(), newStatus);

        order.setStatus(newStatus);
        Order updated = orderRepository.save(order);

        return orderMapper.toDTO(updated);
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

        throw new BadRequestException("Invalid status transition");
    }
}
