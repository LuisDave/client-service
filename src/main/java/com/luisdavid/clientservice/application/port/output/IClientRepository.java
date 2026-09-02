package com.luisdavid.clientservice.application.port.output;

import com.luisdavid.clientservice.domain.model.Client;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para la persistencia de clientes.
 * La capa de aplicación depende de este contrato y no de una tecnología de base de datos concreta.
 */
public interface IClientRepository {

    Client save(Client client);

    Optional<Client> findById(Long clientId);

    List<Client> findAll();
}
