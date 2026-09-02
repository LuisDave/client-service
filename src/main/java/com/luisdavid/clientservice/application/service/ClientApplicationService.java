package com.luisdavid.clientservice.application.service;
import com.luisdavid.clientservice.application.dto.request.CreateClientRequest;
import com.luisdavid.clientservice.application.dto.response.ClientResponse;
import com.luisdavid.clientservice.application.port.input.IClientUseCase;
import com.luisdavid.clientservice.application.port.output.IClientRepository;
import com.luisdavid.clientservice.domain.exception.ClientNotFoundException;
import com.luisdavid.clientservice.domain.model.Client;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
@Service public class ClientApplicationService implements IClientUseCase {
 private final IClientRepository repository; public ClientApplicationService(IClientRepository repository) { this.repository = repository; }
 @Transactional public ClientResponse createClient(CreateClientRequest r) { return ClientResponse.from(repository.save(new Client(null, r.fullName(), r.email(), LocalDateTime.now()))); }
 @Transactional(readOnly = true) public ClientResponse getClientById(Long id) { return ClientResponse.from(repository.findById(id).orElseThrow(() -> new ClientNotFoundException(id))); }
 @Transactional(readOnly = true) public List<ClientResponse> getAllClients() { return repository.findAll().stream().map(ClientResponse::from).toList(); }
}
