package com.luisdavid.clientservice.application.port.output;
import com.luisdavid.clientservice.domain.model.Client;
import java.util.List;
import java.util.Optional;
public interface IClientRepository { Client save(Client client); Optional<Client> findById(Long id); List<Client> findAll(); }
