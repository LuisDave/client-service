package com.luisdavid.clientservice.adapter.output.persistence.repository;

import com.luisdavid.clientservice.adapter.output.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio técnico de Spring Data para la entidad de persistencia de clientes.
 */
public interface SpringDataClientRepository extends JpaRepository<ClientEntity, Long> {
}
