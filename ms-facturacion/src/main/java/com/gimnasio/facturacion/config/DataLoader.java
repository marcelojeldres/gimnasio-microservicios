package com.gimnasio.facturacion.config;

import com.gimnasio.facturacion.model.Factura;
import com.gimnasio.facturacion.repository.FacturaRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Locale;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    private final FacturaRepository facturaRepository;

    public DataLoader(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public void run(String... args) {
        if (facturaRepository.count() > 0) return;

        Faker faker = new Faker(new Locale("es"));
        for (int i = 1; i <= 5; i++) {
            double neto = faker.number().randomDouble(2, 10000, 80000);
            double iva = neto * 0.19;
            Factura f = new Factura();
            f.setArriendoId((long) i);
            f.setRunCliente(faker.number().digits(8) + "-" + faker.number().digit());
            f.setMontoNeto(neto);
            f.setIva(iva);
            f.setMontoTotal(neto + iva);
            f.setFechaEmision(LocalDateTime.now());
            f.setEstado(i % 2 == 0 ? "PAGADA" : "PENDIENTE");
            facturaRepository.save(f);
        }
        System.out.println("[ms-facturacion] Base de datos poblada con 5 facturas.");
    }
}
