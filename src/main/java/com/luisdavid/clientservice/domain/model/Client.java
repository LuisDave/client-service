package com.luisdavid.clientservice.domain.model;

import java.time.LocalDateTime;

/** Representa al cliente que puede actuar como pagador. */
public record Client(Long id, String fullName, String email, LocalDateTime createdAt) { }
