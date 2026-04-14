package com.grupoait.prueba.service;

import com.grupoait.prueba.entity.Driver;

import java.util.List;

public interface DriverService {

    Driver createDriver(Driver driver);

    List<Driver> getActiveDrivers();
}
