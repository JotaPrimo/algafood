package com.algaworks.api.algafood.domain.service;

import com.algaworks.api.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.domain.model.Restaurante;
import com.algaworks.api.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {

    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO
            = "Não existe um cadastro de restaurante com código %d";


    private final RestauranteRepository restauranteRepository;


    private final CadastroCozinhaService cadastroCozinha;

    public CadastroRestauranteService(RestauranteRepository restauranteRepository, CadastroCozinhaService cadastroCozinha) {
        this.restauranteRepository = restauranteRepository;
        this.cadastroCozinha = cadastroCozinha;
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)));
    }
}