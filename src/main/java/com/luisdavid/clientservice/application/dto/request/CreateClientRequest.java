package com.luisdavid.clientservice.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/** Datos requeridos para registrar un cliente pagador. */
public record CreateClientRequest(@NotBlank String fullName, @NotBlank @Email String email) { }
