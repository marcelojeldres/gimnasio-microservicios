package com.gimnasio.resenas.controller;

import com.gimnasio.resenas.model.Resena;
import com.gimnasio.resenas.service.ResenaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResenaController.class)
class ResenaControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ResenaService resenaService;

    @Test
    void debeCrearResena() throws Exception {
        Resena creada = new Resena(1L, 1L, "12345678-9", 5, "Muy bueno", LocalDate.now());
        when(resenaService.save(any(Resena.class))).thenReturn(creada);

        mockMvc.perform(post("/api/v1/resenas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"equipoId":1,"runCliente":"12345678-9","estrellas":5,"comentario":"Muy bueno"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }
}
