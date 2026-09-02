package com.luisdavid.clientservice;

import com.luisdavid.clientservice.application.dto.request.CreateClientRequest;
import com.luisdavid.clientservice.application.port.output.IClientRepository;
import com.luisdavid.clientservice.application.service.ClientApplicationService;
import com.luisdavid.clientservice.domain.exception.ClientNotFoundException;
import com.luisdavid.clientservice.domain.model.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
@DisplayName("Pruebas unitarias de ClientApplicationService")
class ClientApplicationServiceTest {
    @Mock private IClientRepository repository;

    @Test @DisplayName("Debe crear un cliente")
    void shouldCreateClient() {
        when(repository.save(any())).thenReturn(new Client(1L, "Ana López", "ana@example.com", LocalDateTime.now()));
        assertEquals(1L, new ClientApplicationService(repository).createClient(new CreateClientRequest("Ana López", "ana@example.com")).id());
    }

    @Test @DisplayName("Debe fallar cuando el cliente no existe")
    void shouldFailWhenClientDoesNotExist() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class, () -> new ClientApplicationService(repository).getClientById(99L));
    }

    @Test @DisplayName("Debe listar los clientes registrados")
    void shouldListClients() {
        when(repository.findAll()).thenReturn(List.of(new Client(1L, "Ana López", "ana@example.com", LocalDateTime.now())));
        assertEquals(1, new ClientApplicationService(repository).getAllClients().size());
    }
}
