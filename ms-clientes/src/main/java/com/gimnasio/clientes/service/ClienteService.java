package com.gimnasio.clientes.service;

import com.gimnasio.clientes.model.Cliente;
import com.gimnasio.clientes.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAll() {
        log.info("[ms-clientes] Listando todos los clientes");
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        log.info("[ms-clientes] Buscando cliente con ID: {}", id);
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> findByRun(String run) {
        log.info("[ms-clientes] Buscando cliente con RUN: {}", run);
        return clienteRepository.findByRun(run);
    }

    public Cliente save(Cliente cliente) {
        log.info("[ms-clientes] Guardando cliente: {} {}", cliente.getNombres(), cliente.getApellidos());
        return clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        log.warn("[ms-clientes] Eliminando cliente con ID: {}", id);
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
