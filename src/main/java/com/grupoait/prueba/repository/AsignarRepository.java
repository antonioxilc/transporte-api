package com.grupoait.prueba.repository;

import com.grupoait.prueba.entity.Assignment;
import com.grupoait.prueba.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AsignarRepository extends JpaRepository<Assignment, UUID> {

    Optional<Assignment> findByOrder(Order order);
}
