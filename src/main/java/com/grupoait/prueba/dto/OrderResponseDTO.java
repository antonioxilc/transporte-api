package com.grupoait.prueba.dto;

import com.grupoait.prueba.entity.OrderStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderResponseDTO {
    private UUID id;
    private String origin;
    private String destination;
    private OrderStatus status;
}
