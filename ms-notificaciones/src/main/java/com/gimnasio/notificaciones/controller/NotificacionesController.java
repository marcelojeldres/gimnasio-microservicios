package com.gimnasio.notificaciones.controller;

import com.gimnasio.notificaciones.model.NotificacionRequest;
import com.gimnasio.notificaciones.service.NotificacionesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notificaciones")
@Tag(name = "Notificaciones", description = "Envío de correos (simulación local)")
public class NotificacionesController {

    private final NotificacionesService notificacionesService;

    public NotificacionesController(NotificacionesService notificacionesService) {
        this.notificacionesService = notificacionesService;
    }

    @PostMapping("/enviar")
    @Operation(summary = "Enviar notificación")
    public ResponseEntity<String> enviar(@Valid @RequestBody NotificacionRequest request) {
        notificacionesService.enviarNotificacion(request);
        return ResponseEntity.ok("Notificación enviada a " + request.getDestinatario());
    }

    @GetMapping("/health")
    @Operation(summary = "Health check")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("ms-notificaciones activo en puerto 8086");
    }
}
