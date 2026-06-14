package com.gimnasio.sucursales.controller;

import com.gimnasio.sucursales.model.Sucursal;
import com.gimnasio.sucursales.service.SucursalService;
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

@WebMvcTest(SucursalController.class)
class SucursalControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private SucursalService sucursalService;

    @Test
    void debeCrearSucursal() throws Exception {
        Sucursal creada = new Sucursal(1L, "Norte", "Calle 1", "24 horas");
        when(sucursalService.save(any(Sucursal.class))).thenReturn(creada);

        mockMvc.perform(post("/api/v1/sucursales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"nombre":"Norte","direccion":"Calle 1","horario":"24 horas"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Norte"));
    }
}
