package com.gimnasio.facturacion.repository;

import com.gimnasio.facturacion.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByRunCliente(String runCliente);
    List<Factura> findByEstado(String estado);
}
