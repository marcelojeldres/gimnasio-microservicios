package com.gimnasio.resenas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "resenas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del equipo es obligatorio")
    private Long equipoId;

    @NotBlank(message = "El RUN del cliente es obligatorio")
    private String runCliente;

    @NotNull(message = "La calificación es obligatoria")
    @Min(1) @Max(5)
    private Integer estrellas;

    @NotBlank(message = "El comentario no puede estar vacío")
    @Size(max = 500)
    private String comentario;

    private LocalDate fechaPublicacion = LocalDate.now();
}
