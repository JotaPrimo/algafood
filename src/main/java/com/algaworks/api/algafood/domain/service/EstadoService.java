package com.algaworks.api.algafood.domain.service;

import com.algaworks.api.algafood.api.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.model.Estado;
import com.algaworks.api.algafood.domain.repository.EstadoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EstadoService {

    private final EstadoRepository repository;

    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    public List<Estado> buscarTodos() {
        return repository.findAll();
    }

    public Estado buscarPorId(Long id) {
        Objects.requireNonNull("Id do estado não pode ser null");

        Optional<Estado> optionalEstado = repository.findById(id);

        if (optionalEstado.isPresent()) {
            return optionalEstado.get();
        }

        throw new EntidadeNaoEncontradaException(String.format("Estado de id #%d não encontrado", id));
    }
}
