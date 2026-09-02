package com.luisdavid.clientservice;

import com.luisdavid.clientservice.application.dto.request.CreateClientRequest;
import com.luisdavid.clientservice.application.dto.response.ClientResponse;
import com.luisdavid.clientservice.application.port.output.IClientRepository;
import com.luisdavid.clientservice.application.service.ClientApplicationService;
import com.luisdavid.clientservice.domain.exception.ClientNotFoundException;
import com.luisdavid.clientservice.domain.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
@DisplayName("Pruebas unitarias de ClientApplicationService")
class ClientApplicationServiceTest {

    private static final Clock FIXED_CLOCK = Clock.fixed(
            Instant.parse("2026-09-01T12:00:00Z"),
            ZoneOffset.UTC
    );

    @Mock
    private IClientRepository repository;

    private ClientApplicationService clientApplicationService;

    @BeforeEach
    void setUp() {
        clientApplicationService = new ClientApplicationService(repository, FIXED_CLOCK);
    }

    @Test
    @DisplayName("Debe crear un cliente")
    void shouldCreateClient() {
        when(repository.save(any())).thenReturn(new Client(1L, "Ana López", "ana@example.com", LocalDateTime.now()));

        ClientResponse response = clientApplicationService.createClient(
                new CreateClientRequest("Ana López", "ana@example.com")
        );

        assertEquals(1L, response.id());
        verify(repository).save(any(Client.class));
    }

    @Test
    @DisplayName("Debe fallar cuando el cliente no existe")
    void shouldFailWhenClientDoesNotExist() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientApplicationService.getClientById(99L));
    }

    @Test
    @DisplayName("Debe listar los clientes registrados")
    void shouldListClients() {
        when(repository.findAll()).thenReturn(List.of(new Client(1L, "Ana López", "ana@example.com", LocalDateTime.now())));

        assertEquals(1, clientApplicationService.getAllClients().size());
    }
}
