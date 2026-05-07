package com.grupoait.prueba.contoller;


import com.grupoait.prueba.dto.order.OrderRequestDTO;
import com.grupoait.prueba.dto.order.OrderResponseDTO;
import com.grupoait.prueba.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDTO createDriver(@Valid @RequestBody OrderRequestDTO dto) {
        return orderService.createOrder(dto);
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getById(@PathVariable UUID id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<OrderResponseDTO> getAll() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}/status")
    public OrderResponseDTO updateStatus(@PathVariable UUID id, @RequestParam String status) {
    return orderService.updateStatus(id, status);
    }


}
