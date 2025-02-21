package com.algaworks.api.algafood.domain.jap_repository;

import com.algaworks.api.algafood.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepositoryJpa extends JpaRepository<FormaPagamento, Long> {
}