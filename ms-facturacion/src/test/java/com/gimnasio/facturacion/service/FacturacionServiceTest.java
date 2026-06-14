package com.gimnasio.facturacion.service;

import com.gimnasio.facturacion.model.Factura;
import com.gimnasio.facturacion.repository.FacturaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class FacturacionServiceTest {

    @Autowired private FacturacionService facturacionService;
    @MockBean private FacturaRepository facturaRepository;

    @Test
    void debeGenerarFacturaConIva() {
        ReflectionTestUtils.setField(facturacionService, "tasaIva", 0.19);
        when(facturaRepository.save(any(Factura.class))).thenAnswer(inv -> {
            Factura f = inv.getArgument(0);
            f.setId(1L);
            return f;
        });

        Factura resultado = facturacionService.generarFactura(1L, "12345678-9", 100000.0);

        assertEquals(19000.0, resultado.getIva());
        assertEquals(119000.0, resultado.getMontoTotal());
        assertEquals("PENDIENTE", resultado.getEstado());
    }
}
