package com.grupoait.prueba.dto.assignment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AssignmentRequestDTO {

    @NotNull
    private UUID orderId;

    @NotNull
    private UUID driverId;

    private MultipartFile document;

    private MultipartFile image;
}
