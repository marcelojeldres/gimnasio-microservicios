package com.gimnasio.sucursales.config;

import com.gimnasio.sucursales.model.Sucursal;
import com.gimnasio.sucursales.repository.SucursalRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    private final SucursalRepository sucursalRepository;

    public DataLoader(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public void run(String... args) {
        if (sucursalRepository.count() > 0) return;
        Faker faker = new Faker(new Locale("es"));
        String[] horarios = {"Lun-Vie 06:00-22:00", "Lun-Sab 07:00-21:00", "24 horas"};
        for (int i = 0; i < 3; i++) {
            Sucursal s = new Sucursal();
            s.setNombre("Sucursal " + faker.address().city());
            s.setDireccion(faker.address().fullAddress());
            s.setHorario(horarios[i]);
            sucursalRepository.save(s);
        }
        System.out.println("[ms-sucursales] Base de datos poblada con 3 sucursales.");
    }
}
