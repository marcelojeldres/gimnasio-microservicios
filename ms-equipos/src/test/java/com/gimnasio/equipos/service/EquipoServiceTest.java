package com.gimnasio.equipos.service;

import com.gimnasio.equipos.model.Equipo;
import com.gimnasio.equipos.repository.EquipoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class EquipoServiceTest {

    @Autowired
    private EquipoService equipoService;

    @MockBean
    private EquipoRepository equipoRepository;

    @Test
    @DisplayName("Debería guardar un equipo correctamente")
    void debeGuardarEquipoExitosamente() {
        Equipo entrada = new Equipo(null, "Caminadora Pro", "CARDIO", 15000.0, true, "Pantalla táctil");
        Equipo guardado = new Equipo(1L, "Caminadora Pro", "CARDIO", 15000.0, true, "Pantalla táctil");
        when(equipoRepository.save(any(Equipo.class))).thenReturn(guardado);

        Equipo resultado = equipoService.save(entrada);

        assertNotNull(resultado.getId());
        assertEquals("CARDIO", resultado.getCategoria());
        verify(equipoRepository, times(1)).save(entrada);
    }
}
