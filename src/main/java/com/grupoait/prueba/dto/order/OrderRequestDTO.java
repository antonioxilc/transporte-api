package com.grupoait.prueba.dto.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class OrderRequestDTO {

    @NotBlank (message = "El Origen es obligatorio")
    private String origin;

    @NotBlank(message = "El destino es obligatorio")
    private String destination;
}
