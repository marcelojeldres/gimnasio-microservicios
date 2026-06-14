package com.gimnasio.equipos.controller;

import com.gimnasio.equipos.model.Equipo;
import com.gimnasio.equipos.service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equipos")
@Tag(name = "Equipos", description = "Gestión de equipos del gimnasio")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping
    @Operation(summary = "Listar equipos")
    public ResponseEntity<List<Equipo>> listar() {
        return ResponseEntity.ok(equipoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener por ID", description = "Lo consulta ms-resenas para validar que el equipo existe")
    public ResponseEntity<Equipo> obtenerPorId(@PathVariable Long id) {
        return equipoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Listar disponibles")
    public ResponseEntity<List<Equipo>> listarDisponibles() {
        return ResponseEntity.ok(equipoService.findDisponibles());
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Listar por categoría")
    public ResponseEntity<List<Equipo>> listarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(equipoService.findByCategoria(categoria));
    }

    @PostMapping
    @Operation(summary = "Crear equipo")
    public ResponseEntity<Equipo> crear(@Valid @RequestBody Equipo equipo) {
        return new ResponseEntity<>(equipoService.save(equipo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar equipo")
    public ResponseEntity<Equipo> actualizar(@PathVariable Long id, @Valid @RequestBody Equipo equipo) {
        return ResponseEntity.ok(equipoService.actualizar(id, equipo));
    }

    @PatchMapping("/{id}/disponibilidad")
    @Operation(summary = "Cambiar disponibilidad")
    public ResponseEntity<Equipo> cambiarDisponibilidad(@PathVariable Long id, @RequestParam Boolean valor) {
        return ResponseEntity.ok(equipoService.cambiarDisponibilidad(id, valor));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar equipo")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        equipoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
