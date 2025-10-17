package com.algaworks.api.algafood.api.assembler;

import com.algaworks.api.algafood.api.model.input.CidadeInput;
import com.algaworks.api.algafood.api.model.input.CozinhaInput;
import com.algaworks.api.algafood.domain.model.Cidade;
import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputDissasembler {
    private final ModelMapper modelMapper;

    public CozinhaInputDissasembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Cozinha toModel(CozinhaInput cozinhaInput) {
        return modelMapper.map(cozinhaInput, Cozinha.class);
    }

    public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
        modelMapper.map(cozinhaInput, cozinha);
    }
}
