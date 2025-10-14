package com.algaworks.api.algafood.api.assembler;

import com.algaworks.api.algafood.api.model.CozinhaModel;
import com.algaworks.api.algafood.api.model.RestauranteModel;
import com.algaworks.api.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestauranteModelAssembler {

    private final ModelMapper modelMapper;

    public RestauranteModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RestauranteModel toModel(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteModel.class);
    }

    public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .toList();
    }
}
