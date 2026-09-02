package com.luisdavid.clientservice.domain.exception;
/** Indica que no existe el cliente solicitado. */
public class ClientNotFoundException extends RuntimeException { public ClientNotFoundException(Long id) { super("Client not found with id: " + id); } }
