package com.gimnasio.facturacion.controller;

import com.gimnasio.facturacion.model.Factura;
import com.gimnasio.facturacion.service.FacturacionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacturacionController.class)
class FacturacionControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private FacturacionService facturacionService;

    @Test
    void debeGenerarFactura() throws Exception {
        Factura f = new Factura(1L, 1L, "12345678-9", 100000.0, 19000.0, 119000.0, LocalDateTime.now(), "PENDIENTE");
        when(facturacionService.generarFactura(anyLong(), anyString(), anyDouble())).thenReturn(f);

        mockMvc.perform(post("/api/v1/facturas/generar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"arriendoId\":1,\"runCliente\":\"12345678-9\",\"montoNeto\":100000}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }
}
