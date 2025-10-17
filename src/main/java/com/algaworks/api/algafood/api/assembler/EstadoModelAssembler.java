package com.algaworks.api.algafood.api.assembler;

import com.algaworks.api.algafood.api.model.EstadoModel;
import com.algaworks.api.algafood.api.model.RestauranteModel;
import com.algaworks.api.algafood.domain.model.Estado;
import com.algaworks.api.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstadoModelAssembler {

    private final ModelMapper modelMapper;

    public EstadoModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EstadoModel toModel(Estado estado) {
        return modelMapper.map(estado, EstadoModel.class);
    }

    public List<EstadoModel> toCollectionModel(List<Estado> estados) {
        return estados.stream()
                .map(estado -> toModel(estado))
                .toList();
    }
}
