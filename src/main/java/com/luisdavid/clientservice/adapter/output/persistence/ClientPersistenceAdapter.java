package com.luisdavid.clientservice.adapter.output.persistence;

import com.luisdavid.clientservice.adapter.output.persistence.entity.ClientEntity;
import com.luisdavid.clientservice.adapter.output.persistence.repository.SpringDataClientRepository;
import com.luisdavid.clientservice.application.port.output.IClientRepository;
import com.luisdavid.clientservice.domain.model.Client;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/**
 * Implementa el puerto de persistencia de clientes mediante Spring Data JPA.
 */
@Component
public class ClientPersistenceAdapter implements IClientRepository {
    private final SpringDataClientRepository springDataClientRepository;

    public ClientPersistenceAdapter(SpringDataClientRepository springDataClientRepository) {
        this.springDataClientRepository = springDataClientRepository;
    }

    @Override
    public Client save(Client client) {
        ClientEntity savedClient = springDataClientRepository.save(toEntity(client));
        return toDomain(savedClient);
    }

    @Override
    public Optional<Client> findById(Long clientId) {
        return springDataClientRepository.findById(clientId)
                .map(this::toDomain);
    }

    @Override
    public List<Client> findAll() {
        return springDataClientRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    private ClientEntity toEntity(Client client) {
        return new ClientEntity(
                client.id(),
                client.fullName(),
                client.email(),
                client.createdAt()
        );
    }

    private Client toDomain(ClientEntity clientEntity) {
        return new Client(
                clientEntity.getId(),
                clientEntity.getFullName(),
                clientEntity.getEmail(),
                clientEntity.getCreatedAt()
        );
    }
}
