package com.algaworks.api.algafood.api.assembler;

import com.algaworks.api.algafood.api.model.CozinhaModel;
import com.algaworks.api.algafood.api.model.RestauranteModel;
import com.algaworks.api.algafood.api.model.input.CozinhaIdInput;
import com.algaworks.api.algafood.api.model.input.RestauranteInput;
import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.domain.model.Restaurante;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class RestauranteInputlDissassembler {
    public Restaurante toModel(RestauranteInput restauranteInput) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
        restaurante.setNome(restauranteInput.getNome());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());
        restaurante.setCozinha(cozinha);

        return restaurante;
    }
}
