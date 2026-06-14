package com.gimnasio.sucursales.controller;

import com.gimnasio.sucursales.model.Sucursal;
import com.gimnasio.sucursales.service.SucursalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursales")
@Tag(name = "Sucursales", description = "Gestión de sucursales del gimnasio")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @GetMapping
    @Operation(summary = "Listar sucursales")
    public ResponseEntity<List<Sucursal>> listar() {
        return ResponseEntity.ok(sucursalService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener por ID")
    public ResponseEntity<Sucursal> obtener(@PathVariable Long id) {
        return sucursalService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear sucursal")
    public ResponseEntity<Sucursal> crear(@Valid @RequestBody Sucursal sucursal) {
        return new ResponseEntity<>(sucursalService.save(sucursal), HttpStatus.CREATED);
    }
}
