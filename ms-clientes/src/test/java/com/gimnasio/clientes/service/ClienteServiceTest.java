package com.gimnasio.clientes.service;

import com.gimnasio.clientes.model.Cliente;
import com.gimnasio.clientes.repository.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Debería listar todos los clientes")
    void debeListarClientes() {
        Cliente cliente = new Cliente(1L, "12345678-9", "Juan", "Pérez", null, "juan@test.cl");
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> resultado = clienteService.findAll();

        assertEquals(1, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombres());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debería guardar un cliente correctamente")
    void debeGuardarCliente() {
        Cliente cliente = new Cliente(null, "12345678-9", "María", "González", null, "maria@test.cl");
        Cliente guardado = new Cliente(1L, "12345678-9", "María", "González", null, "maria@test.cl");
        when(clienteRepository.save(any(Cliente.class))).thenReturn(guardado);

        Cliente resultado = clienteService.save(cliente);

        assertNotNull(resultado.getId());
        assertEquals("María", resultado.getNombres());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    @DisplayName("Debería buscar cliente por RUN")
    void debeBuscarPorRun() {
        Cliente cliente = new Cliente(1L, "12345678-9", "Pedro", "López", null, "pedro@test.cl");
        when(clienteRepository.findByRun("12345678-9")).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = clienteService.findByRun("12345678-9");

        assertTrue(resultado.isPresent());
        assertEquals("Pedro", resultado.get().getNombres());
    }
}
