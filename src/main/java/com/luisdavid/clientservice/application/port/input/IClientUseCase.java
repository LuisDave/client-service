package com.luisdavid.clientservice.application.port.input;
import com.luisdavid.clientservice.application.dto.request.CreateClientRequest;
import com.luisdavid.clientservice.application.dto.response.ClientResponse;
import java.util.List;
/** Define las operaciones de cliente disponibles para los adaptadores de entrada. */
public interface IClientUseCase {
    ClientResponse createClient(CreateClientRequest request);
    ClientResponse getClientById(Long id);
    List<ClientResponse> getAllClients();
}
