package com.gimnasio.resenas.config;

import com.gimnasio.resenas.model.Resena;
import com.gimnasio.resenas.repository.ResenaRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    private final ResenaRepository resenaRepository;

    public DataLoader(ResenaRepository resenaRepository) {
        this.resenaRepository = resenaRepository;
    }

    @Override
    public void run(String... args) {
        if (resenaRepository.count() > 0) return;
        Faker faker = new Faker(new Locale("es"));
        for (int i = 1; i <= 5; i++) {
            Resena r = new Resena();
            r.setEquipoId((long) i);
            r.setRunCliente(faker.number().digits(8) + "-" + faker.number().digit());
            r.setEstrellas(faker.number().numberBetween(3, 6));
            r.setComentario(faker.lorem().sentence(8));
            r.setFechaPublicacion(LocalDate.now());
            resenaRepository.save(r);
        }
        System.out.println("[ms-resenas] Base de datos poblada (requiere ms-equipos para validar en runtime).");
    }
}
