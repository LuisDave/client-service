package com.luisdavid.clientservice.application.port.input;

import com.luisdavid.clientservice.application.dto.request.CreateClientRequest;
import com.luisdavid.clientservice.application.dto.response.ClientResponse;

import java.util.List;

/**
 * Define los casos de uso disponibles para los adaptadores de entrada del servicio de clientes.
 */
public interface IClientUseCase {

    /**
     * Registra un nuevo cliente pagador.
     *
     * @param request datos validados del cliente.
     * @return cliente persistido.
     */
    ClientResponse createClient(CreateClientRequest request);

    /**
     * Obtiene un cliente por su identificador.
     *
     * @param clientId identificador del cliente.
     * @return cliente encontrado.
     */
    ClientResponse getClientById(Long clientId);

    /**
     * Obtiene todos los clientes registrados.
     *
     * @return lista de clientes, vacía si no existen registros.
     */
    List<ClientResponse> getAllClients();
}
