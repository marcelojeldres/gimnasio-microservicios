package com.gimnasio.notificaciones.service;

import com.gimnasio.notificaciones.model.NotificacionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificacionesService {

    @Value("${notificaciones.from}")
    private String from;

    public void enviarNotificacion(NotificacionRequest request) {
        String cuerpo = construirMensaje(request);
        log.info("[ms-notificaciones] SIMULACIÓN EMAIL");
        log.info("  De: {}", from);
        log.info("  Para: {}", request.getDestinatario());
        log.info("  Asunto: {}", request.getAsunto());
        log.info("  Tipo: {}", request.getTipo());
        log.info("  Mensaje:\n{}", cuerpo);
    }

    private String construirMensaje(NotificacionRequest request) {
        return "Gimnasio - Sistema de Arriendos\n" +
                "─────────────────────────────────\n\n" +
                request.getMensaje() +
                "\n\n─────────────────────────────────\n" +
                "Este es un mensaje automático. No responder.";
    }
}
