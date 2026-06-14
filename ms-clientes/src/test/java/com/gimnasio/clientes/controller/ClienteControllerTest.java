package com.gimnasio.clientes.controller;

import com.gimnasio.clientes.model.Cliente;
import com.gimnasio.clientes.service.ClienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    @DisplayName("HTTP POST - Debería responder 201 Created al registrar un cliente")
    void debeCrearClientePorHttp() throws Exception {
        Cliente clienteCreado = new Cliente(1L, "12345678-9", "Ana", "Torres", null, "ana@test.cl");
        when(clienteService.save(any(Cliente.class))).thenReturn(clienteCreado);

        String json = """
                {
                    "run": "12345678-9",
                    "nombres": "Ana",
                    "apellidos": "Torres",
                    "correo": "ana@test.cl"
                }
                """;

        mockMvc.perform(post("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombres").value("Ana"))
                .andExpect(jsonPath("$.run").value("12345678-9"));
    }
}
