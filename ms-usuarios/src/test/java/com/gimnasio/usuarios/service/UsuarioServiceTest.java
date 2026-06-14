package com.gimnasio.usuarios.service;

import com.gimnasio.usuarios.model.Usuario;
import com.gimnasio.usuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class UsuarioServiceTest {

    @Autowired private UsuarioService usuarioService;
    @MockBean private UsuarioRepository usuarioRepository;

    @Test
    void debeGenerarTokenEnLogin() {
        Usuario u = new Usuario(1L, "admin", "1234", "ADMIN");
        when(usuarioRepository.findByUsername("admin")).thenReturn(Optional.of(u));

        String token = usuarioService.login("admin", "1234");

        assertNotNull(token);
        assertTrue(token.contains("."));
    }
}
