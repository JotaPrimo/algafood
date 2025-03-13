package com.algaworks.api.algafood.domain.service;

import com.algaworks.api.algafood.api.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.domain.model.Restaurante;
import com.algaworks.api.algafood.domain.repository.CozinhaRepository;
import com.algaworks.api.algafood.domain.repository.RestauranteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CadastroRestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;

    @Autowired
    public CadastroRestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {

        Cozinha cozinha = cozinhaRepository
                .findById(restaurante.getCozinha().getId()).orElseThrow(
                        () -> new EntidadeNaoEncontradaException(String.format("Cozinha de id %d não encontrada", restaurante.getCozinha().getId())));

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscarPorId(Long id) {
        Optional<Restaurante> optionalRestaurante = restauranteRepository.findById(id);

        return optionalRestaurante.orElse(null);
    }

    @Transactional
    public void remover(Long id) {
        Restaurante restaurante = buscarPorId(id);

        if (restaurante == null) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante #%d não encontrado", id));
        }

        restauranteRepository.delete(restaurante);
    }

    @Transactional
    public void remover(Restaurante restaurante) {
        Objects.requireNonNull(restaurante, "Objeto de restaurante não pode ser null");

        if (restaurante.getId() == null) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante #%d não encontrado", restaurante.getId()));
        }

        restauranteRepository.delete(restaurante);
    }
}