package com.gimnasio.sucursales.service;

import com.gimnasio.sucursales.model.Sucursal;
import com.gimnasio.sucursales.repository.SucursalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class SucursalServiceTest {

    @Autowired private SucursalService sucursalService;
    @MockBean private SucursalRepository sucursalRepository;

    @Test
    void debeListarSucursales() {
        Sucursal s = new Sucursal(1L, "Central", "Av. Principal 100", "Lun-Vie 06:00-22:00");
        when(sucursalRepository.findAll()).thenReturn(List.of(s));
        assertEquals(1, sucursalService.findAll().size());
    }
}
