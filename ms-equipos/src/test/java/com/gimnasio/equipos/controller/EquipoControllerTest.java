package com.gimnasio.equipos.controller;

import com.gimnasio.equipos.model.Equipo;
import com.gimnasio.equipos.service.EquipoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EquipoController.class)
class EquipoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipoService equipoService;

    @Test
    @DisplayName("HTTP POST - Debería responder 201 Created al registrar un equipo")
    void debeCrearEquipoPorHttp() throws Exception {
        Equipo creado = new Equipo(1L, "Mancuernas Pro", "FUERZA", 8500.0, true, "Alta calidad");
        when(equipoService.save(any(Equipo.class))).thenReturn(creado);

        String json = """
                {
                    "nombre": "Mancuernas Pro",
                    "categoria": "FUERZA",
                    "costoArriendoDia": 8500.0,
                    "disponible": true,
                    "descripcion": "Alta calidad"
                }
                """;

        mockMvc.perform(post("/api/v1/equipos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Mancuernas Pro"));
    }
}
