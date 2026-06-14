package com.gimnasio.resenas.controller;

import com.gimnasio.resenas.model.Resena;
import com.gimnasio.resenas.service.ResenaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resenas")
@Tag(name = "Reseñas", description = "Reseñas de equipos (comunica con ms-equipos)")
public class ResenaController {

    private final ResenaService resenaService;

    public ResenaController(ResenaService resenaService) {
        this.resenaService = resenaService;
    }

    @PostMapping
    @Operation(summary = "Crear reseña", description = "Valida el equipo en ms-equipos antes de guardar")
    public ResponseEntity<Resena> crear(@Valid @RequestBody Resena resena) {
        return new ResponseEntity<>(resenaService.save(resena), HttpStatus.CREATED);
    }

    @GetMapping("/equipo/{equipoId}")
    @Operation(summary = "Listar por equipo")
    public ResponseEntity<List<Resena>> porEquipo(@PathVariable Long equipoId) {
        return ResponseEntity.ok(resenaService.findByEquipoId(equipoId));
    }
}
