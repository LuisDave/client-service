package com.luisdavid.clientservice.adapter.input.web.controller;
import com.luisdavid.clientservice.application.dto.request.CreateClientRequest;
import com.luisdavid.clientservice.application.dto.response.ClientResponse;
import com.luisdavid.clientservice.application.port.input.IClientUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/** Adaptador HTTP para registrar y consultar clientes. */
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final IClientUseCase clientUseCase;

    public ClientController(IClientUseCase clientUseCase) {
        this.clientUseCase = clientUseCase;
    }

    @PostMapping
    @Operation(summary = "Crear cliente", description = "Registra un cliente que podrá actuar como pagador.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente creado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "409", description = "Correo electrónico ya registrado")
    })
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody CreateClientRequest request) {
        ClientResponse response = clientUseCase.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{clientId}")
    @Operation(summary = "Consultar cliente por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ClientResponse getClientById(@PathVariable Long clientId) {
        return clientUseCase.getClientById(clientId);
    }

    @GetMapping
    @Operation(summary = "Listar clientes")
    @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida correctamente")
    public List<ClientResponse> getAllClients() {
        return clientUseCase.getAllClients();
    }
}
