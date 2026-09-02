package com.luisdavid.clientservice.application.dto.response;
import com.luisdavid.clientservice.domain.model.Client;
import java.time.LocalDateTime;
public record ClientResponse(Long id, String fullName, String email, LocalDateTime createdAt) { public static ClientResponse from(Client c) { return new ClientResponse(c.id(), c.fullName(), c.email(), c.createdAt()); } }
