package com.algaworks.api.algafood.domain.service;

import com.algaworks.api.algafood.api.exceptions.EntidadeEmUsoException;
import com.algaworks.api.algafood.api.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.model.Cidade;
import com.algaworks.api.algafood.domain.jap_repository.CidadeRepositoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CadastroCidadeService {
    private final CidadeRepositoryJpa repository;

    public CadastroCidadeService(CidadeRepositoryJpa repository) {
        this.repository = repository;
    }

    public List<Cidade> buscarTodas() {
        return repository.findAll();
    }

    public Cidade buscarPorId(Long id) {

        Optional<Cidade> optionalCidade = repository.findById(id);

        if (optionalCidade.isPresent()) {
            return optionalCidade.get();
        }

        throw new EntidadeNaoEncontradaException(String.format("Cidade de id #%d não encontrada", id));
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        Objects.requireNonNull(cidade);

        return repository.save(cidade);
    }

    @Transactional
    public void remover(Long id) {
        try {
            Cidade cidade = repository.findById(id)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Cidade #%d não encontrada", id)));

            repository.delete(cidade);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cidade #%d não pode ser deletada, pois está em uso", id));
        }
    }
}
