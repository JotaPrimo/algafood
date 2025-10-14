package com.algaworks.api.algafood.api.assembler;

import com.algaworks.api.algafood.api.model.input.RestauranteInput;
import com.algaworks.api.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputlDissassembler {

    private final ModelMapper modelMapper;

    public RestauranteInputlDissassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Restaurante toModel(RestauranteInput restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }
}