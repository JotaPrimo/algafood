package com.algaworks.api.algafood.domain.repository;

import com.algaworks.api.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cozinha, Long> {

}
