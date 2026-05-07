package com.grupoait.prueba.mapper;

import com.grupoait.prueba.dto.assignment.AssignmentResponseDTO;
import com.grupoait.prueba.entity.Assignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssignmentMapper {

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "driver.id", target = "driverId")
    @Mapping(source = "driver.name", target = "driverName")
    AssignmentResponseDTO toDTO(Assignment assignment);


}
