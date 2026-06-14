package com.gimnasio.auth.service;

import com.gimnasio.auth.model.Usuario;
import com.gimnasio.auth.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class AuthServiceTest {

    @Autowired private AuthService authService;
    @Autowired private PasswordEncoder passwordEncoder;
    @MockBean private UsuarioRepository usuarioRepository;

    @Test
    void debeHacerLoginExitoso() {
        Usuario u = new Usuario(1L, "admin", passwordEncoder.encode("admin123"), "ROLE_ADMIN", true);
        when(usuarioRepository.findByUsername("admin")).thenReturn(Optional.of(u));

        Map<String, String> result = authService.login("admin", "admin123");

        assertEquals("Login exitoso", result.get("mensaje"));
        assertEquals("admin", result.get("username"));
    }
}
