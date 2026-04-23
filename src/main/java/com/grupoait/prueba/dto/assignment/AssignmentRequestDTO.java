package com.grupoait.prueba.dto.assignment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class AssignmentRequestDTO {

    @NotNull
    private UUID ordeId;

    @NotNull
    private UUID driverId;

    private MultipartFile file;

    private MultipartFile image;
}
