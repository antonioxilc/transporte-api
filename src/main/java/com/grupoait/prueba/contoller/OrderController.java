package com.grupoait.prueba.contoller;

import com.grupoait.prueba.dto.OrderRequestDTO;
import com.grupoait.prueba.dto.OrderResponseDTO;
import com.grupoait.prueba.entity.Order;
import com.grupoait.prueba.mapper.OrderMapper;
import com.grupoait.prueba.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public OrderResponseDTO create(@RequestBody OrderRequestDTO dto) {

        Order order = new Order();
        order.setOrigin(dto.getOrigin());
        order.setDestination(dto.getDestination());

        Order saved = orderService.createOrder(order);

        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(saved.getId());
        response.setOrigin(saved.getOrigin());
        response.setDestination(saved.getDestination());
        response.setStatus(saved.getStatus());

        return response;
    }
    @GetMapping("/{id}")
    public OrderResponseDTO getById(@PathVariable UUID id) {

        Order order = orderService.getOrderById(id);

        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(order.getId());
        response.setOrigin(order.getOrigin());
        response.setDestination(order.getDestination());
        response.setStatus(order.getStatus());

        return response;
    }

    @GetMapping
    public List<OrderResponseDTO> getAll() {

        return orderService.getOrders().stream().map(order -> {
            OrderResponseDTO dto = new OrderResponseDTO();
            dto.setId(order.getId());
            dto.setOrigin(order.getOrigin());
            dto.setDestination(order.getDestination());
            dto.setStatus(order.getStatus());
            return dto;
        }).toList();
    }
}
