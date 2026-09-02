package com.luisdavid.clientservice.adapter.output.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/** Representación JPA de la tabla de clientes. */
@Entity
@Table(name = "clients")
public class ClientEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "full_name", nullable = false) private String fullName;
    @Column(nullable = false, unique = true) private String email;
    @Column(name = "created_at", nullable = false) private LocalDateTime createdAt;
    protected ClientEntity() { }
    public ClientEntity(Long id, String fullName, String email, LocalDateTime createdAt) { this.id = id; this.fullName = fullName; this.email = email; this.createdAt = createdAt; }
    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
