package com.gimnasio.equipos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La categoría es obligatoria")
    @Column(nullable = false)
    private String categoria;

    @NotNull(message = "El costo de arriendo por día es obligatorio")
    @Min(value = 0, message = "El costo no puede ser negativo")
    @Column(nullable = false)
    private Double costoArriendoDia;

    @Column(nullable = false)
    private Boolean disponible = true;

    private String descripcion;
}
