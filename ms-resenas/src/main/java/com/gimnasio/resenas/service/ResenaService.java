package com.gimnasio.resenas.service;

import com.gimnasio.resenas.model.Resena;
import com.gimnasio.resenas.repository.ResenaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ResenaService {

    private final ResenaRepository resenaRepository;
    private final RestTemplate restTemplate;

    @Value("${ms.equipos.url}")
    private String equiposUrl;

    public ResenaService(ResenaRepository resenaRepository, RestTemplate restTemplate) {
        this.resenaRepository = resenaRepository;
        this.restTemplate = restTemplate;
    }

    public Resena save(Resena resena) {
        log.info("[ms-resenas] Validando equipo ID {} con ms-equipos", resena.getEquipoId());
        String url = equiposUrl + "/api/v1/equipos/" + resena.getEquipoId();

        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("El equipo especificado no existe.");
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            log.error("[ms-resenas] Error al comunicarse con ms-equipos: {}", e.getMessage());
            throw new RuntimeException("Error de comunicación: ms-equipos no responde o el equipo no existe.");
        }

        log.info("[ms-resenas] Equipo validado. Guardando reseña.");
        return resenaRepository.save(resena);
    }

    public List<Resena> findByEquipoId(Long equipoId) {
        return resenaRepository.findByEquipoId(equipoId);
    }
}
