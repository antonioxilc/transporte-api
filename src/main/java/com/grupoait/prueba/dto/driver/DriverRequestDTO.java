package com.grupoait.prueba.dto.driver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DriverRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String licenseNumber;

    @NotNull
    private Boolean active;
}
