package com.grupoait.prueba.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DriverDTO {
    private UUID id;
    private String name;
    private String licenseNumber;
}
