package com.gimnasio.clientes.config;

import com.gimnasio.clientes.model.Cliente;
import com.gimnasio.clientes.repository.ClienteRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    private final ClienteRepository clienteRepository;

    public DataLoader(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run(String... args) {
        if (clienteRepository.count() > 0) {
            return;
        }

        Faker faker = new Faker(new Locale("es"));

        for (int i = 0; i < 10; i++) {
            Cliente cliente = new Cliente();
            cliente.setRun(faker.number().digits(8) + "-" + faker.number().digit());
            cliente.setNombres(faker.name().firstName());
            cliente.setApellidos(faker.name().lastName());
            cliente.setFechaNacimiento(faker.date().birthday());
            cliente.setCorreo(faker.internet().emailAddress());
            clienteRepository.save(cliente);
        }

        System.out.println("[ms-clientes] Base de datos poblada con 10 clientes de prueba.");
    }
}
