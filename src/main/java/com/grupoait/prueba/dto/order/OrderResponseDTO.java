package com.grupoait.prueba.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderResponseDTO {

    private UUID id;
    private String status;
    private String origin;
    private String destination;
    private LocalDateTime localDateTime;
    private LocalDateTime updateAT;
}
