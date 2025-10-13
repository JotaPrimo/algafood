package com.algaworks.api.algafood.api.assembler;

import com.algaworks.api.algafood.api.model.CozinhaModel;
import com.algaworks.api.algafood.api.model.RestauranteModel;
import com.algaworks.api.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestauranteModelAssembler {
    public RestauranteModel toModel(Restaurante restaurante) {
        CozinhaModel cozinhaModel = new CozinhaModel();
        cozinhaModel.setId(restaurante.getId());
        cozinhaModel.setNome(restaurante.getCozinha().getNome());

        RestauranteModel restauranteModel = new RestauranteModel();
        restauranteModel.setId(restaurante.getId());
        restauranteModel.setNome(restaurante.getNome());
        restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteModel.setCozinha(cozinhaModel);

        return restauranteModel;
    }

    public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .toList();
    }
}
