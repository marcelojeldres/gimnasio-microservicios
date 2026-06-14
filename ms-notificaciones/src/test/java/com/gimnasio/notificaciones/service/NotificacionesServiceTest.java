package com.gimnasio.notificaciones.service;

import com.gimnasio.notificaciones.model.NotificacionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class NotificacionesServiceTest {

    @Autowired private NotificacionesService notificacionesService;

    @Test
    void debeSimularEnvioDeCorreo() {
        NotificacionRequest req = new NotificacionRequest("test@mail.cl", "Asunto", "Mensaje", "ARRIENDO_CREADO");
        assertDoesNotThrow(() -> notificacionesService.enviarNotificacion(req));
    }
}
