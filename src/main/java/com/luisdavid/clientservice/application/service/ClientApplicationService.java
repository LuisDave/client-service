package com.luisdavid.clientservice.application.service;

import com.luisdavid.clientservice.application.dto.request.CreateClientRequest;
import com.luisdavid.clientservice.application.dto.response.ClientResponse;
import com.luisdavid.clientservice.application.port.input.IClientUseCase;
import com.luisdavid.clientservice.application.port.output.IClientRepository;
import com.luisdavid.clientservice.domain.exception.ClientNotFoundException;
import com.luisdavid.clientservice.domain.model.Client;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/** Coordina los casos de uso de consulta y registro de clientes. */
@Service
public class ClientApplicationService implements IClientUseCase {

    private final IClientRepository repository;

    public ClientApplicationService(IClientRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public ClientResponse createClient(CreateClientRequest request) {
        Client client = new Client(null, request.fullName(), request.email(), java.time.LocalDateTime.now());
        return ClientResponse.from(repository.save(client));
    }

    @Override
    @Transactional(readOnly = true)
    public ClientResponse getClientById(Long clientId) {
        Client client = repository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
        return ClientResponse.from(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponse> getAllClients() {
        return repository.findAll().stream().map(ClientResponse::from).toList();
    }
}
