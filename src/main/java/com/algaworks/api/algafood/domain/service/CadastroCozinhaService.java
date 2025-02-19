package com.algaworks.api.algafood.domain.service;

import com.algaworks.api.algafood.api.exceptions.EntidadeEmUsoException;
import com.algaworks.api.algafood.api.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.domain.repository.CozinhaRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class CadastroCozinhaService {

    private CozinhaRepository cozinhaRepository;

    public Cozinha buscar(Long id) {
        Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(id);

        return cozinhaOptional.orElse(null);
    }

    public Cozinha buscarOuFalhar(Long id) {
        Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(id);

        if (cozinhaOptional.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("Registro de cozinha com id %d não encontrado", id));
        }

        return cozinhaOptional.get();
    }

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        Objects.requireNonNull(cozinha, "Cozinha não pode ser null");
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            Cozinha cozinha = buscar(id);
            cozinhaRepository.delete(cozinha);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cozinha com código %d", id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
        }
    }
}