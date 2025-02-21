package com.algaworks.api.algafood.domain.service;

import com.algaworks.api.algafood.api.exceptions.EntidadeEmUsoException;
import com.algaworks.api.algafood.api.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.model.Estado;
import com.algaworks.api.algafood.domain.jap_repository.EstadoRepositoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EstadoService {

    private final EstadoRepositoryJpa repository;

    public EstadoService(EstadoRepositoryJpa repository) {
        this.repository = repository;
    }

    public List<Estado> buscarTodos() {
        return repository.findAll();
    }

    public Estado buscarPorId(Long id) {

        Optional<Estado> optionalEstado = repository.findById(id);

        if (optionalEstado.isPresent()) {
            return optionalEstado.get();
        }

        throw new EntidadeNaoEncontradaException(String.format("Estado de id #%d não encontrado", id));
    }

    @Transactional
    public Estado salvar(Estado estado) {
        Objects.requireNonNull(estado);

        return repository.save(estado);
    }

    @Transactional
    public void remover(Long id) {
        try {
            Estado estado = repository.findById(id)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Estado #%d não encontrado", id)));

            repository.delete(estado);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Estado #%d não pode ser deletado, pois está em uso", id));
        }
    }
}