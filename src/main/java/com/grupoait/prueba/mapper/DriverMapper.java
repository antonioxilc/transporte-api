package com.grupoait.prueba.mapper;

import com.grupoait.prueba.entity.Driver;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface DriverMapper {

    Driver toEntity(DriverDTO dto);

    DriverDTO toDTO(Driver driver);
}
