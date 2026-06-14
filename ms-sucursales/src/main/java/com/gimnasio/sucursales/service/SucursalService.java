package com.gimnasio.sucursales.service;

import com.gimnasio.sucursales.model.Sucursal;
import com.gimnasio.sucursales.repository.SucursalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SucursalService {

    private final SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    public List<Sucursal> findAll() {
        log.info("[ms-sucursales] Listando sucursales");
        return sucursalRepository.findAll();
    }

    public Optional<Sucursal> findById(Long id) {
        return sucursalRepository.findById(id);
    }

    public Sucursal save(Sucursal sucursal) {
        log.info("[ms-sucursales] Registrando sucursal: {}", sucursal.getNombre());
        return sucursalRepository.save(sucursal);
    }
}
