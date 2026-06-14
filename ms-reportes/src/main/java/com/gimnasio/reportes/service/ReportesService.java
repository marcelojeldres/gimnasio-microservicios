package com.gimnasio.reportes.service;

import com.gimnasio.reportes.model.ResumenReporte;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ReportesService {

    private final RestTemplate restTemplate;

    @Value("${ms.clientes.url}") private String clientesUrl;
    @Value("${ms.equipos.url}") private String equiposUrl;
    @Value("${ms.resenas.url}") private String resenasUrl;
    @Value("${ms.facturacion.url}") private String facturacionUrl;

    public ReportesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResumenReporte generarResumen() {
        log.info("[ms-reportes] Generando resumen del sistema");
        ResumenReporte resumen = new ResumenReporte();

        try {
            List clientes = restTemplate.getForObject(clientesUrl + "/api/v1/clientes", List.class);
            resumen.setTotalClientes(clientes != null ? clientes.size() : 0);
        } catch (Exception e) {
            log.warn("[ms-reportes] No se pudo obtener clientes: {}", e.getMessage());
        }

        try {
            List equipos = restTemplate.getForObject(equiposUrl + "/api/v1/equipos", List.class);
            List disponibles = restTemplate.getForObject(equiposUrl + "/api/v1/equipos/disponibles", List.class);
            resumen.setTotalEquipos(equipos != null ? equipos.size() : 0);
            resumen.setEquiposDisponibles(disponibles != null ? disponibles.size() : 0);
        } catch (Exception e) {
            log.warn("[ms-reportes] No se pudo obtener equipos: {}", e.getMessage());
        }

        try {
            List facturas = restTemplate.getForObject(facturacionUrl + "/api/v1/facturas", List.class);
            List pagadas = restTemplate.getForObject(facturacionUrl + "/api/v1/facturas/estado/PAGADA", List.class);
            resumen.setTotalFacturas(facturas != null ? facturas.size() : 0);
            resumen.setFacturasPagadas(pagadas != null ? pagadas.size() : 0);

            if (pagadas != null) {
                double ingresos = pagadas.stream()
                        .mapToDouble(f -> {
                            Object monto = ((Map) f).get("montoTotal");
                            return monto != null ? Double.parseDouble(monto.toString()) : 0;
                        }).sum();
                resumen.setIngresosTotales(ingresos);
            }
        } catch (Exception e) {
            log.warn("[ms-reportes] No se pudo obtener facturas: {}", e.getMessage());
        }

        try {
            List resenas = restTemplate.getForObject(resenasUrl + "/api/v1/resenas/equipo/1", List.class);
            resumen.setTotalResenas(resenas != null ? resenas.size() : 0);
        } catch (Exception e) {
            log.warn("[ms-reportes] No se pudo obtener reseñas: {}", e.getMessage());
        }

        return resumen;
    }

    public List getFacturasPorCliente(String run) {
        log.info("[ms-reportes] Consultando facturas del cliente {}", run);
        return restTemplate.getForObject(facturacionUrl + "/api/v1/facturas/cliente/" + run, List.class);
    }
}
