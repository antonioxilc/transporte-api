package com.grupoait.prueba.repository;

import com.grupoait.prueba.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {
    List<Driver> findByActiveTrue();
    boolean existsByLicenseNumber(String licenseNumber);
}
