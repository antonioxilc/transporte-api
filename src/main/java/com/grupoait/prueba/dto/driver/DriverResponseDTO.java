package com.grupoait.prueba.dto.driver;

import lombok.Data;

import java.util.UUID;

@Data
public class DriverResponseDTO {

    private UUID id;
    private String name;
    private String licenseNumber;
    private Boolean active;

}
