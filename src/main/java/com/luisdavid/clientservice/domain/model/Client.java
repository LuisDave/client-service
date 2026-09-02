package com.luisdavid.clientservice.domain.model;

import java.time.LocalDateTime;

/**
 * Representa al cliente que puede actuar como pagador dentro del flujo de pagos.
 *
 * @param id identificador persistente del cliente; es {@code null} antes de registrarlo.
 * @param fullName nombre completo del cliente.
 * @param email correo electrónico único del cliente.
 * @param createdAt instante UTC en que se registró el cliente.
 */
public record Client(Long id, String fullName, String email, LocalDateTime createdAt) {
}
