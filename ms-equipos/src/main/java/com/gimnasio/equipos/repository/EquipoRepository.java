package com.gimnasio.equipos.repository;

import com.gimnasio.equipos.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findByDisponible(Boolean disponible);

    List<Equipo> findByCategoria(String categoria);
}
