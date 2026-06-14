package com.gimnasio.auth.controller;

import com.gimnasio.auth.model.Usuario;
import com.gimnasio.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "Autenticación y gestión de usuarios")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/usuarios")
    @Operation(summary = "Listar usuarios")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(authService.findAll());
    }

    @PostMapping("/registro")
    @Operation(summary = "Registrar usuario")
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(authService.registrar(usuario), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(authService.login(body.get("username"), body.get("password")));
    }

    @PatchMapping("/usuarios/{id}/desactivar")
    @Operation(summary = "Desactivar usuario")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        authService.desactivar(id);
        return ResponseEntity.noContent().build();
    }
}
