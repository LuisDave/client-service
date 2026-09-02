package com.luisdavid.clientservice.application.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public record CreateClientRequest(@NotBlank String fullName, @NotBlank @Email String email) { }
