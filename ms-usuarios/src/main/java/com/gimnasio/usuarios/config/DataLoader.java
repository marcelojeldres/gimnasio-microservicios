package com.gimnasio.usuarios.config;

import com.gimnasio.usuarios.model.Usuario;
import com.gimnasio.usuarios.repository.UsuarioRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() > 0) return;
        Faker faker = new Faker(new Locale("es"));
        String[] roles = {"ADMIN", "EMPLEADO"};
        for (int i = 0; i < 3; i++) {
            Usuario u = new Usuario();
            u.setUsername("user" + (i + 1));
            u.setPassword("pass" + (i + 1));
            u.setRol(roles[i % 2]);
            usuarioRepository.save(u);
        }
        System.out.println("[ms-usuarios] Base de datos poblada con 3 usuarios.");
    }
}
