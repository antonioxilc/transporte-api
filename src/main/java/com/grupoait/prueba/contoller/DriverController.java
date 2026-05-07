package com.grupoait.prueba.contoller;

import com.grupoait.prueba.dto.driver.DriverRequestDTO;
import com.grupoait.prueba.dto.driver.DriverResponseDTO;
import com.grupoait.prueba.mapper.DriverMapper;
import com.grupoait.prueba.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;
    private final DriverMapper driverMapper;

    @PostMapping
    public DriverResponseDTO create(@RequestBody DriverRequestDTO dto) {
        return driverMapper.toDTO(
                driverService.createDriver(driverMapper.toEntity(dto))
        );
    }
}
