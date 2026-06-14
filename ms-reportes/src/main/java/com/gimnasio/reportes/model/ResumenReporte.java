package com.gimnasio.reportes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenReporte {
    private int totalClientes;
    private int totalEquipos;
    private int equiposDisponibles;
    private int totalFacturas;
    private int facturasPagadas;
    private int totalResenas;
    private Double ingresosTotales;
}
