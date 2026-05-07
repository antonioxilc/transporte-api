package com.grupoait.prueba.service.impl;

import com.grupoait.prueba.dto.assignment.AssignmentRequestDTO;
import com.grupoait.prueba.dto.assignment.AssignmentResponseDTO;
import com.grupoait.prueba.entity.Assignment;
import com.grupoait.prueba.entity.Driver;
import com.grupoait.prueba.entity.Order;
import com.grupoait.prueba.entity.OrderStatus;
import com.grupoait.prueba.exception.ResourceNotFoundException;
import com.grupoait.prueba.mapper.AssignmentMapper;
import com.grupoait.prueba.repository.AssignmentRepository;
import com.grupoait.prueba.repository.DriverRepository;
import com.grupoait.prueba.repository.OrderRepository;
import com.grupoait.prueba.service.AssignmentService;

import jakarta.transaction.TransactionScoped;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final AssignmentMapper assignmentMapper;

    private static final String UPLOAD_DIR = "uploads/";


    @Override
    @Transactional
    public AssignmentResponseDTO assign(AssignmentRequestDTO request) {

        // 1. Buscar Order
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        // 2. Validar estado
        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new ResourceNotFoundException("El pedido debe estar en estado CREADO");
        }
        // 3. Buscar Driver
        Driver driver = driverRepository.findById(request.getDriverId())
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found"));

        // 4. Validar driver activo
        if (!driver.isActive()) {
            throw new ResourceNotFoundException("Driver is not active");
        }

        // 5. Validar que no esté asignada
        boolean alreadyAssigned = assignmentRepository.existsByOrderId(order.getId());

        if (alreadyAssigned) {
            throw new ResourceNotFoundException("Order already assigned");
        }

        // Guardar archivos
        String documentPath = saveFile(request.getDocument());
        String imagePath = saveFile(request.getImage());

        // 6. Crear Assignment
        Assignment assignment = new Assignment();
        assignment.setOrder(order);
        assignment.setDriver(driver);

        assignment.setDocumentPath(documentPath);
        assignment.setImagePath(imagePath);

        Assignment saved = assignmentRepository.save(assignment);

        // 7. Cambiar estado de Order
        order.setStatus(OrderStatus.IN_TRANSIT);
        orderRepository.save(order);

        // 8. Mapear respuesta
        return assignmentMapper.toDTO(saved);

    }

    @Override
    public AssignmentResponseDTO getById(UUID id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found"));

        return assignmentMapper.toDTO(assignment);
    }
    private String saveFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);

            // Crear carpeta si no existe
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            // Nombre único
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path filePath = uploadPath.resolve(fileName);

            // Guardar archivo
            Files.copy(file.getInputStream(), filePath);

            return filePath.toString();

        } catch (IOException e) {
            throw new RuntimeException("Error saving file", e);
        }
    }
}
