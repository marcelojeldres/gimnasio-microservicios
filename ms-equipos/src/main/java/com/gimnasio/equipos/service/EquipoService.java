package com.gimnasio.equipos.service;

import com.gimnasio.equipos.model.Equipo;
import com.gimnasio.equipos.repository.EquipoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public List<Equipo> findAll() {
        log.info("[ms-equipos] Listando todos los equipos");
        return equipoRepository.findAll();
    }

    public Optional<Equipo> findById(Long id) {
        log.info("[ms-equipos] Buscando equipo con ID: {}", id);
        return equipoRepository.findById(id);
    }

    public List<Equipo> findDisponibles() {
        log.info("[ms-equipos] Listando equipos disponibles");
        return equipoRepository.findByDisponible(true);
    }

    public List<Equipo> findByCategoria(String categoria) {
        log.info("[ms-equipos] Buscando equipos de categoría: {}", categoria);
        return equipoRepository.findByCategoria(categoria);
    }

    public Equipo save(Equipo equipo) {
        log.info("[ms-equipos] Guardando equipo: {}", equipo.getNombre());
        return equipoRepository.save(equipo);
    }

    public Equipo actualizar(Long id, Equipo equipoNuevo) {
        log.info("[ms-equipos] Actualizando equipo ID: {}", id);
        Equipo equipoExistente = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + id));

        equipoExistente.setNombre(equipoNuevo.getNombre());
        equipoExistente.setCategoria(equipoNuevo.getCategoria());
        equipoExistente.setCostoArriendoDia(equipoNuevo.getCostoArriendoDia());
        equipoExistente.setDescripcion(equipoNuevo.getDescripcion());

        return equipoRepository.save(equipoExistente);
    }

    public Equipo cambiarDisponibilidad(Long id, Boolean disponible) {
        log.info("[ms-equipos] Cambiando disponibilidad del equipo ID: {} a {}", id, disponible);
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + id));
        equipo.setDisponible(disponible);
        return equipoRepository.save(equipo);
    }

    public void delete(Long id) {
        log.warn("[ms-equipos] Eliminando equipo con ID: {}", id);
        if (!equipoRepository.existsById(id)) {
            throw new RuntimeException("Equipo no encontrado con ID: " + id);
        }
        equipoRepository.deleteById(id);
    }
}
