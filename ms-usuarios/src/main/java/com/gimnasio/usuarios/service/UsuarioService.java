package com.gimnasio.usuarios.service;

import com.gimnasio.usuarios.model.Usuario;
import com.gimnasio.usuarios.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrar(Usuario usuario) {
        log.info("[ms-usuarios] Registrando usuario: {}", usuario.getUsername());
        return usuarioRepository.save(usuario);
    }

    public String login(String username, String password) {
        log.info("[ms-usuarios] Intento de login: {}", username);
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String header = Base64.getEncoder().encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes());
        String payloadJson = "{\"sub\":\"" + usuario.getUsername() + "\",\"rol\":\"" + usuario.getRol() + "\"}";
        String payload = Base64.getEncoder().encodeToString(payloadJson.getBytes());
        String signature = Base64.getEncoder().encodeToString("firma-secreta-gimnasio".getBytes());

        return header + "." + payload + "." + signature;
    }
}
