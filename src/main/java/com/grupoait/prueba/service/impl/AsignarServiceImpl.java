package com.grupoait.prueba.service.impl;

import com.grupoait.prueba.entity.Assignment;
import com.grupoait.prueba.entity.Driver;
import com.grupoait.prueba.entity.Order;
import com.grupoait.prueba.entity.OrderStatus;
import com.grupoait.prueba.repository.AsignarRepository;
import com.grupoait.prueba.repository.DriverRepository;
import com.grupoait.prueba.repository.OrderRepository;
import com.grupoait.prueba.service.AsignarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AsignarServiceImpl implements AsignarService {

    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final AsignarRepository assignmentRepository;

    @Override
    public void asignarDriver(UUID orderId, UUID driverId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        if (!driver.isActive()) {
            throw new RuntimeException("Driver is not active");
        }

        if (order.getStatus() != OrderStatus.CREATED) {
            throw new RuntimeException("Order must be in CREATED state");
        }

        if (assignmentRepository.findByOrder(order).isPresent()) {
            throw new RuntimeException("Order already assigned");
        }

        Assignment assignment = new Assignment();
        assignment.setOrder(order);
        assignment.setDriver(driver);
        assignment.setAssignedAt(LocalDateTime.now());

        assignmentRepository.save(assignment);
    }
}
