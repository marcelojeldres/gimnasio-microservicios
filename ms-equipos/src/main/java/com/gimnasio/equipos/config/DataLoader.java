package com.gimnasio.equipos.config;

import com.gimnasio.equipos.model.Equipo;
import com.gimnasio.equipos.repository.EquipoRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    private final EquipoRepository equipoRepository;

    public DataLoader(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @Override
    public void run(String... args) {
        if (equipoRepository.count() > 0) {
            return;
        }

        Faker faker = new Faker(new Locale("es"));
        String[] nombres = {"Caminadora Elíptica Pro", "Set Mancuernas", "Bicicleta Spinning", "Prensa de Piernas", "Banco Abdominales"};
        String[] categorias = {"CARDIO", "FUERZA", "CARDIO", "FUERZA", "FUNCIONAL"};
        String[] descripciones = {
                "Caminadora con pantalla táctil.",
                "Mancuernas engomadas profesionales.",
                "Bicicleta para spinning de alta intensidad.",
                "Máquina de fuerza para tren inferior.",
                "Banco reclinable para zona media."
        };

        for (int i = 0; i < nombres.length; i++) {
            Equipo equipo = new Equipo();
            equipo.setNombre(nombres[i]);
            equipo.setCategoria(categorias[i]);
            equipo.setCostoArriendoDia(faker.number().randomDouble(2, 5000, 25000));
            equipo.setDisponible(true);
            equipo.setDescripcion(descripciones[i]);
            equipoRepository.save(equipo);
        }

        System.out.println("[ms-equipos] Base de datos poblada con 5 equipos de prueba.");
    }
}
