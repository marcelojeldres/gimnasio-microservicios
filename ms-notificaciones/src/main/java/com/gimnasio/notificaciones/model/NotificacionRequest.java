package com.gimnasio.notificaciones.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionRequest {

    @NotBlank @Email
    private String destinatario;
    @NotBlank
    private String asunto;
    @NotBlank
    private String mensaje;
    private String tipo;
}
