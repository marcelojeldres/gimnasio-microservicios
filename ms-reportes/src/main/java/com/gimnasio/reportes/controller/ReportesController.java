package com.gimnasio.reportes.controller;

import com.gimnasio.reportes.model.ResumenReporte;
import com.gimnasio.reportes.service.ReportesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
@Tag(name = "Reportes", description = "Resumen agregado del sistema")
public class ReportesController {

    private final ReportesService reportesService;

    public ReportesController(ReportesService reportesService) {
        this.reportesService = reportesService;
    }

    @GetMapping("/resumen")
    @Operation(summary = "Resumen ejecutivo")
    public ResponseEntity<ResumenReporte> resumen() {
        return ResponseEntity.ok(reportesService.generarResumen());
    }

    @GetMapping("/cliente/{run}")
    @Operation(summary = "Facturas de un cliente")
    public ResponseEntity<List> historialCliente(@PathVariable String run) {
        return ResponseEntity.ok(reportesService.getFacturasPorCliente(run));
    }
}
