package com.grupoait.prueba.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.grupoait.prueba.dto.assignment.AssignmentResponseDTO;
import com.grupoait.prueba.entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderResponseDTO {

    private UUID id;
    private OrderStatus status;
    private String origin;
    private String destination;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
