package com.algaworks.api.algafood.domain.jap_repository;

import com.algaworks.api.algafood.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepositoryJpa extends JpaRepository<Permissao, Long> {
}
