package com.grupoait.prueba.repository;

import com.grupoait.prueba.entity.Assignar;
import com.grupoait.prueba.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AsignarRepository extends JpaRepository<Assignar, UUID> {

    Optional<Assignar> findByOrder(Order order);
}
