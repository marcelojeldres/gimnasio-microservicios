package com.gimnasio.facturacion.controller;

import com.gimnasio.facturacion.model.Factura;
import com.gimnasio.facturacion.service.FacturacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/facturas")
@Tag(name = "Facturas", description = "Facturación con IVA 19%")
public class FacturacionController {

    private final FacturacionService facturacionService;

    public FacturacionController(FacturacionService facturacionService) {
        this.facturacionService = facturacionService;
    }

    @GetMapping
    @Operation(summary = "Listar facturas")
    public ResponseEntity<List<Factura>> listar() {
        return ResponseEntity.ok(facturacionService.findAll());
    }

    @GetMapping("/cliente/{run}")
    @Operation(summary = "Facturas por cliente")
    public ResponseEntity<List<Factura>> porCliente(@PathVariable String run) {
        return ResponseEntity.ok(facturacionService.findByCliente(run));
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Facturas por estado")
    public ResponseEntity<List<Factura>> porEstado(@PathVariable String estado) {
        return ResponseEntity.ok(facturacionService.findByEstado(estado));
    }

    @PostMapping("/generar")
    @Operation(summary = "Generar factura")
    public ResponseEntity<Factura> generar(@RequestBody Map<String, Object> body) {
        Long arriendoId = Long.valueOf(body.get("arriendoId").toString());
        String runCliente = body.get("runCliente").toString();
        Double montoNeto = Double.valueOf(body.get("montoNeto").toString());
        return new ResponseEntity<>(facturacionService.generarFactura(arriendoId, runCliente, montoNeto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/pagar")
    @Operation(summary = "Marcar como pagada")
    public ResponseEntity<Factura> pagar(@PathVariable Long id) {
        return ResponseEntity.ok(facturacionService.pagarFactura(id));
    }

    @PatchMapping("/{id}/anular")
    @Operation(summary = "Anular factura")
    public ResponseEntity<Factura> anular(@PathVariable Long id) {
        return ResponseEntity.ok(facturacionService.anularFactura(id));
    }
}
