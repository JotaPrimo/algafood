package com.algaworks.api.algafood.domain.jap_repository;

import com.algaworks.api.algafood.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepositoryJpa extends JpaRepository<Estado, Long> {
}
