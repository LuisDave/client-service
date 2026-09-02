package com.luisdavid.clientservice.domain.exception;

/**
 * Indica que no existe un cliente con el identificador solicitado.
 */
public final class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(Long clientId) {
        super("No existe un cliente con el id: " + clientId);
    }
}
