package com.luisdavid.clientservice.application.dto.response;

import com.luisdavid.clientservice.domain.model.Client;

import java.time.LocalDateTime;

/**
 * Representación pública de un cliente registrado.
 */
public record ClientResponse(Long id, String fullName, String email, LocalDateTime createdAt) {

    /**
     * Convierte el modelo de dominio a la representación expuesta por la API.
     *
     * @param client cliente obtenido o creado por el caso de uso.
     * @return respuesta que puede enviarse al consumidor HTTP.
     */
    public static ClientResponse from(Client client) {
        return new ClientResponse(
                client.id(),
                client.fullName(),
                client.email(),
                client.createdAt()
        );
    }
}
