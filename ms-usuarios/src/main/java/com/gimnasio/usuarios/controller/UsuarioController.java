package com.gimnasio.usuarios.controller;

import com.gimnasio.usuarios.model.Usuario;
import com.gimnasio.usuarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Registro y login de usuarios del sistema")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    @Operation(summary = "Registrar usuario")
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.registrar(usuario), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Retorna un token JWT simulado")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credenciales) {
        String token = usuarioService.login(credenciales.get("username"), credenciales.get("password"));
        return ResponseEntity.ok(Map.of("token", token));
    }
}
