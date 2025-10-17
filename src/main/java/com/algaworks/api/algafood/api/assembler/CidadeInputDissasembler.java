package com.algaworks.api.algafood.api.assembler;

import com.algaworks.api.algafood.api.model.input.CidadeInput;
import com.algaworks.api.algafood.domain.model.Cidade;
import com.algaworks.api.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputDissasembler {
    private final ModelMapper modelMapper;

    public CidadeInputDissasembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Cidade toModel(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeInput, cidade);
    }
}
