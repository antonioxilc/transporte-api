package com.grupoait.prueba.mapper;
import com.grupoait.prueba.dto.order.OrderRequestDTO;
import com.grupoait.prueba.dto.order.OrderResponseDTO;
import com.grupoait.prueba.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "assignment", ignore = true)
    Order toEntity(OrderRequestDTO dto);

      OrderResponseDTO toDTO(Order order);

}
