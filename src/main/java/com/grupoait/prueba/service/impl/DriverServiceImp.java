package com.grupoait.prueba.service.impl;

import com.grupoait.prueba.entity.Driver;
import com.grupoait.prueba.repository.DriverRepository;
import com.grupoait.prueba.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImp implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    public Driver createDriver(Driver driver) {

        if (driverRepository.existsByLicenseNumber(driver.getLicenseNumber())) {
            throw new RuntimeException("License already exists");
        }

        driver.setActive(true);

        return driverRepository.save(driver);
    }

    @Override
    public List<Driver> getActiveDrivers() {
        return driverRepository.findByActiveTrue();
    }
}
