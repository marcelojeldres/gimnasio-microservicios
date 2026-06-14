package com.gimnasio.facturacion.service;

import com.gimnasio.facturacion.model.Factura;
import com.gimnasio.facturacion.repository.FacturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class FacturacionService {

    private final FacturaRepository facturaRepository;

    @Value("${facturacion.iva}")
    private Double tasaIva;

    public FacturacionService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    public List<Factura> findByCliente(String run) {
        return facturaRepository.findByRunCliente(run);
    }

    public List<Factura> findByEstado(String estado) {
        return facturaRepository.findByEstado(estado);
    }

    public Factura generarFactura(Long arriendoId, String runCliente, Double montoNeto) {
        log.info("[ms-facturacion] Generando factura para arriendo {}", arriendoId);
        double iva = montoNeto * tasaIva;
        Factura factura = new Factura();
        factura.setArriendoId(arriendoId);
        factura.setRunCliente(runCliente);
        factura.setMontoNeto(montoNeto);
        factura.setIva(iva);
        factura.setMontoTotal(montoNeto + iva);
        factura.setFechaEmision(LocalDateTime.now());
        factura.setEstado("PENDIENTE");
        return facturaRepository.save(factura);
    }

    public Factura pagarFactura(Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));
        factura.setEstado("PAGADA");
        return facturaRepository.save(factura);
    }

    public Factura anularFactura(Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));
        factura.setEstado("ANULADA");
        return facturaRepository.save(factura);
    }
}
