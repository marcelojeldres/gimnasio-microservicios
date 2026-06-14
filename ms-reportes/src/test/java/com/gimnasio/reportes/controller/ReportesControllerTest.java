package com.gimnasio.reportes.controller;

import com.gimnasio.reportes.model.ResumenReporte;
import com.gimnasio.reportes.service.ReportesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportesController.class)
class ReportesControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ReportesService reportesService;

    @Test
    void debeRetornarResumen() throws Exception {
        when(reportesService.generarResumen()).thenReturn(new ResumenReporte(5, 10, 7, 3, 2, 4, 150000.0));

        mockMvc.perform(get("/api/v1/reportes/resumen"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalClientes").value(5))
                .andExpect(jsonPath("$.totalEquipos").value(10));
    }
}
