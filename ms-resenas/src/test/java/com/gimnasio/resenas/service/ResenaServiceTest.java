package com.gimnasio.resenas.service;

import com.gimnasio.resenas.model.Resena;
import com.gimnasio.resenas.repository.ResenaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResenaServiceTest {

    @Mock private ResenaRepository resenaRepository;
    @Mock private RestTemplate restTemplate;
    @InjectMocks private ResenaService resenaService;

    @Test
    @DisplayName("Debería guardar reseña si ms-equipos confirma que el equipo existe")
    void debeGuardarConComunicacionExitosa() {
        ReflectionTestUtils.setField(resenaService, "equiposUrl", "http://localhost:8082");

        Resena entrada = new Resena(null, 1L, "12345678-9", 5, "Excelente", LocalDate.now());
        Resena guardada = new Resena(1L, 1L, "12345678-9", 5, "Excelente", LocalDate.now());

        when(restTemplate.getForEntity(anyString(), eq(Object.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));
        when(resenaRepository.save(any(Resena.class))).thenReturn(guardada);

        Resena resultado = resenaService.save(entrada);

        assertEquals(1L, resultado.getId());
        verify(restTemplate).getForEntity("http://localhost:8082/api/v1/equipos/1", Object.class);
        verify(resenaRepository).save(entrada);
    }
}
