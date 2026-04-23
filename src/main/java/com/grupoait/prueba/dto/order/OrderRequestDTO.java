package com.grupoait.prueba.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderRequestDTO {

    @NotBlank
    private String origin;
    @NotBlank
    private String destination;
}
