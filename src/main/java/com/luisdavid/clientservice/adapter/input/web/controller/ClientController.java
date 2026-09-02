package com.luisdavid.clientservice.adapter.input.web.controller;
import com.luisdavid.clientservice.application.dto.request.CreateClientRequest;
import com.luisdavid.clientservice.application.dto.response.ClientResponse;
import com.luisdavid.clientservice.application.port.input.IClientUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/** Adaptador HTTP para registrar y consultar clientes. */
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final IClientUseCase useCase;
    public ClientController(IClientUseCase useCase) { this.useCase = useCase; }
    @PostMapping @Operation(summary = "Crear cliente")
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody CreateClientRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(useCase.createClient(request)); }
    @GetMapping("/{id}") @Operation(summary = "Consultar cliente por ID")
    public ClientResponse getClientById(@PathVariable Long id) { return useCase.getClientById(id); }
    @GetMapping @Operation(summary = "Listar clientes")
    public List<ClientResponse> getAllClients() { return useCase.getAllClients(); }
}
