package com.gimnasio.reportes.service;

import com.gimnasio.reportes.model.ResumenReporte;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportesServiceTest {

    @Mock private RestTemplate restTemplate;
    @InjectMocks private ReportesService reportesService;

    @Test
    void debeGenerarResumenConCerosSiServiciosNoResponden() {
        ReflectionTestUtils.setField(reportesService, "clientesUrl", "http://localhost:8081");
        ReflectionTestUtils.setField(reportesService, "equiposUrl", "http://localhost:8082");
        ReflectionTestUtils.setField(reportesService, "resenasUrl", "http://localhost:8083");
        ReflectionTestUtils.setField(reportesService, "facturacionUrl", "http://localhost:8084");

        when(restTemplate.getForObject(anyString(), eq(List.class))).thenThrow(new RuntimeException("offline"));

        ResumenReporte resumen = reportesService.generarResumen();

        assertNotNull(resumen);
        assertEquals(0, resumen.getTotalClientes());
    }
}
