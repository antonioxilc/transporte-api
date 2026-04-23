package com.grupoait.prueba.service;

import com.grupoait.prueba.dto.assignment.AssignmentResponseDTO;
import com.grupoait.prueba.dto.assignment.AssignmentRequestDTO;

import java.util.UUID;

public interface AssignmentService {

AssignmentResponseDTO assign(AssignmentRequestDTO requestDTO);

AssignmentResponseDTO getById(UUID id);
}
