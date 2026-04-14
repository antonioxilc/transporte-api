package com.grupoait.prueba.mapper;


import com.grupoait.prueba.dto.OrderRequestDTO;
import com.grupoait.prueba.dto.OrderResponseDTO;
import com.grupoait.prueba.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderRequestDTO dto);

    OrderResponseDTO toDTO(Order order);

}
