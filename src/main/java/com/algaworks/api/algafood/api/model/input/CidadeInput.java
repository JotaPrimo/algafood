package com.algaworks.api.algafood.api.model.input;

import com.algaworks.api.algafood.api.model.EstadoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {
    private String nome;
    private EstadoModel estado;
}
