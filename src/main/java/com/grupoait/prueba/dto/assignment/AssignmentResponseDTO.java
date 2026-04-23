package com.grupoait.prueba.dto.assignment;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AssignmentResponseDTO {

    private UUID Id;

    private UUID orderId;

    private UUID driverId;

    private String driverName;

    private String documentPath;

    private String imagePath;

    private LocalDateTime assignedAt;
}
