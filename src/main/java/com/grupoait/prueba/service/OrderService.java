package com.grupoait.prueba.service;

import com.grupoait.prueba.entity.Order;
import com.grupoait.prueba.entity.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order createOrder(Order order);

    Order getOrderById(UUID id);

    List<Order> getOrders();


    Order updateStatus(UUID orderId, OrderStatus status);
}
