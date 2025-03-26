package com.algaworks.api.algafood.domain.repository;

import com.algaworks.api.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
    // containing -> like
    List<Cozinha> findAllByNomeContaining(String nome);
    Optional<Cozinha> findByNome(String nome);
}