package com.algaworks.api.algafood.domain.service;

import com.algaworks.api.algafood.api.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.domain.model.Restaurante;
import com.algaworks.api.algafood.domain.jap_repository.CozinhaRepositoryJpa;
import com.algaworks.api.algafood.domain.jap_repository.RestauranteRepositoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CadastroRestauranteService {

    private final RestauranteRepositoryJpa restauranteRepositoryJpa;
    private final CozinhaRepositoryJpa cozinhaRepositoryJpa;

    @Autowired
    public CadastroRestauranteService(RestauranteRepositoryJpa restauranteRepositoryJpa, CozinhaRepositoryJpa cozinhaRepositoryJpa) {
        this.restauranteRepositoryJpa = restauranteRepositoryJpa;
        this.cozinhaRepositoryJpa = cozinhaRepositoryJpa;
    }

    public List<Restaurante> listar() {
        return restauranteRepositoryJpa.findAll();
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {

        Cozinha cozinha = cozinhaRepositoryJpa
                .findById(restaurante.getCozinha().getId()).orElseThrow(
                        () -> new EntidadeNaoEncontradaException(String.format("Cozinha de id %d não encontrada", restaurante.getCozinha().getId())));

        restaurante.setCozinha(cozinha);

        return restauranteRepositoryJpa.save(restaurante);
    }

    public Restaurante buscarPorId(Long id) {
        Optional<Restaurante> optionalRestaurante = restauranteRepositoryJpa.findById(id);

        return optionalRestaurante.orElse(null);
    }

    @Transactional
    public void remover(Long id) {
        Restaurante restaurante = buscarPorId(id);

        if (restaurante == null) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante #%d não encontrado", id));
        }

        restauranteRepositoryJpa.delete(restaurante);
    }

    @Transactional
    public void remover(Restaurante restaurante) {
        Objects.requireNonNull(restaurante, "Objeto de restaurante não pode ser null");

        if (restaurante.getId() == null) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante #%d não encontrado", restaurante.getId()));
        }

        restauranteRepositoryJpa.delete(restaurante);
    }
}
