package com.luisdavid.clientservice.adapter.output.persistence;

import com.luisdavid.clientservice.adapter.output.persistence.entity.ClientEntity;
import com.luisdavid.clientservice.adapter.output.persistence.repository.SpringDataClientRepository;
import com.luisdavid.clientservice.application.port.output.IClientRepository;
import com.luisdavid.clientservice.domain.model.Client;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/** Implementa la persistencia de clientes con Spring Data JPA. */
@Component
public class ClientPersistenceAdapter implements IClientRepository {
    private final SpringDataClientRepository repository;
    public ClientPersistenceAdapter(SpringDataClientRepository repository) { this.repository = repository; }
    @Override public Client save(Client client) { return toDomainClient(repository.save(toClientEntity(client))); }
    @Override public Optional<Client> findById(Long clientId) { return repository.findById(clientId).map(this::toDomainClient); }
    @Override public List<Client> findAll() { return repository.findAll().stream().map(this::toDomainClient).toList(); }
    private ClientEntity toClientEntity(Client client) { return new ClientEntity(client.id(), client.fullName(), client.email(), client.createdAt()); }
    private Client toDomainClient(ClientEntity entity) { return new Client(entity.getId(), entity.getFullName(), entity.getEmail(), entity.getCreatedAt()); }
}
