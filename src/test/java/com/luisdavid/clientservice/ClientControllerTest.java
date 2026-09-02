package com.luisdavid.clientservice;

import com.luisdavid.clientservice.adapter.input.web.controller.ClientController;
import com.luisdavid.clientservice.adapter.input.web.exception.GlobalExceptionHandler;
import com.luisdavid.clientservice.application.dto.response.ClientResponse;
import com.luisdavid.clientservice.application.port.input.IClientUseCase;
import com.luisdavid.clientservice.domain.exception.ClientNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
@DisplayName("Pruebas HTTP de ClientController")
class ClientControllerTest {

    @Mock
    private IClientUseCase useCase;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ClientController(useCase))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("Debe crear un cliente")
    void shouldCreateClient() throws Exception {
        when(useCase.createClient(any())).thenReturn(
                new ClientResponse(1L, "Ana", "ana@example.com", LocalDateTime.now())
        );

        mockMvc.perform(post("/api/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fullName\":\"Ana\",\"email\":\"ana@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("Debe devolver 404 cuando no existe el cliente")
    void shouldReturnNotFound() throws Exception {
        when(useCase.getClientById(9L)).thenThrow(new ClientNotFoundException(9L));

        mockMvc.perform(get("/api/v1/clients/9"))
                .andExpect(status().isNotFound());
    }
}
