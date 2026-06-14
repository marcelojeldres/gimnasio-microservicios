package com.gimnasio.notificaciones.controller;

import com.gimnasio.notificaciones.service.NotificacionesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificacionesController.class)
class NotificacionesControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private NotificacionesService notificacionesService;

    @Test
    void debeEnviarNotificacion() throws Exception {
        mockMvc.perform(post("/api/v1/notificaciones/enviar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"destinatario":"test@mail.cl","asunto":"Hola","mensaje":"Test","tipo":"GENERAL"}
                                """))
                .andExpect(status().isOk());
    }
}
