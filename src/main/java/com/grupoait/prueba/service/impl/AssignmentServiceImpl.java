package com.grupoait.prueba.service.impl;

import com.grupoait.prueba.dto.assignment.AssignmentRequestDTO;
import com.grupoait.prueba.dto.assignment.AssignmentResponseDTO;
import com.grupoait.prueba.entity.Assignment;
import com.grupoait.prueba.entity.Driver;
import com.grupoait.prueba.entity.Order;
import com.grupoait.prueba.entity.OrderStatus;
import com.grupoait.prueba.mapper.AssignmentMapper;
import com.grupoait.prueba.repository.AssignmentRepository;
import com.grupoait.prueba.repository.DriverRepository;
import com.grupoait.prueba.repository.OrderRepository;
import com.grupoait.prueba.service.AssignmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final AssignmentMapper assignmentMapper;


    @Override
    public AssignmentResponseDTO assign(AssignmentRequestDTO request) {

        // 1. Buscar Order
        Order order = orderRepository.findById(request.getOrdeId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // 2. Validar estado
        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new RuntimeException("El pedido debe estar en estado CREADO");
        }
        // 3. Buscar Driver
        Driver driver = driverRepository.findById(request.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        // 4. Validar driver activo
        if (!driver.isActive()) {
            throw new RuntimeException("Driver is not active");
        }

        // 5. Validar que no esté asignada
        boolean alreadyAssigned = assignmentRepository.existsByOrderId(order.getId());

        if (alreadyAssigned) {
            throw new RuntimeException("Order already assigned");
        }

        // 6. Crear Assignment
        Assignment assignment = new Assignment();
        assignment.setOrder(order);
        assignment.setDriver(driver);

        // 🔥 (archivos se manejan después)
        // assignment.setDocumentPath(...)
        // assignment.setImagePath(...)

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
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        return assignmentMapper.toDTO(assignment);
    }
}
