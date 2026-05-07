package com.grupoait.prueba.mapper;

import com.grupoait.prueba.dto.driver.DriverRequestDTO;
import com.grupoait.prueba.dto.driver.DriverResponseDTO;
import com.grupoait.prueba.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    @Mapping(target = "id", ignore = true)
    Driver toEntity(DriverRequestDTO dto);

    DriverResponseDTO toDTO(Driver driver);
}
