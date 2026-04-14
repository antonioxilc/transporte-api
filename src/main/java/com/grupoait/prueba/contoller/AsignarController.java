package com.grupoait.prueba.contoller;

import com.grupoait.prueba.service.AsignarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/asignar")
@RequiredArgsConstructor
public class AsignarController {

    private final AsignarService assignmentService;

    @PostMapping
    public String assignDriver(@RequestParam UUID orderId,
                               @RequestParam UUID driverId) {

        assignmentService.asignarDriver(orderId, driverId);
        return "La asignación se creo correctamente";
    }
}
