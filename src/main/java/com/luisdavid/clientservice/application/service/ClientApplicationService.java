package com.luisdavid.clientservice.application.service;

import com.luisdavid.clientservice.application.dto.request.CreateClientRequest;
import com.luisdavid.clientservice.application.dto.response.ClientResponse;
import com.luisdavid.clientservice.application.port.input.IClientUseCase;
import com.luisdavid.clientservice.application.port.output.IClientRepository;
import com.luisdavid.clientservice.domain.exception.ClientNotFoundException;
import com.luisdavid.clientservice.domain.model.Client;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Coordina los casos de uso de registro y consulta de clientes.
 */
@Service
public class ClientApplicationService implements IClientUseCase {

    private final IClientRepository clientRepository;
    private final Clock clock;

    public ClientApplicationService(IClientRepository clientRepository, Clock clock) {
        this.clientRepository = clientRepository;
        this.clock = clock;
    }

    @Override
    @Transactional
    public ClientResponse createClient(CreateClientRequest request) {
        // Se toma el tiempo desde una dependencia para mantener fechas reproducibles en pruebas.
        Client client = new Client(
                null,
                request.fullName(),
                request.email(),
                LocalDateTime.now(clock)
        );
        return ClientResponse.from(clientRepository.save(client));
    }

    @Override
    @Transactional(readOnly = true)
    public ClientResponse getClientById(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
        return ClientResponse.from(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll().stream()
                .map(ClientResponse::from)
                .toList();
    }
}
