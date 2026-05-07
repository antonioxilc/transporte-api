package com.grupoait.prueba.service;

import com.grupoait.prueba.dto.order.OrderRequestDTO;
import com.grupoait.prueba.dto.order.OrderResponseDTO;
import com.grupoait.prueba.entity.Order;
import com.grupoait.prueba.entity.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO order);

    OrderResponseDTO getOrderById(UUID id);

    List<OrderResponseDTO> getOrders();

    OrderResponseDTO updateStatus(UUID id, String status);

}
