package com.algaworks.api.algafood.api.assembler;

import com.algaworks.api.algafood.api.model.input.EstadoInput;
import com.algaworks.api.algafood.api.model.input.RestauranteInput;
import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.domain.model.Estado;
import com.algaworks.api.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EstadoInputlDissassembler {

    private final ModelMapper modelMapper;

    public EstadoInputlDissassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Estado toModel(EstadoInput estadoInput) {
        return modelMapper.map(estadoInput, Estado.class);
    }

    public void copyToDomainObject(EstadoInput estadoInput, Estado estado) {
        modelMapper.map(estadoInput, estado);
    }

}