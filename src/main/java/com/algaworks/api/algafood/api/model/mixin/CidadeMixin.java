package com.algaworks.api.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CidadeMixin {

    @JsonIgnoreProperties(value = {"nome"}, allowGetters = true)
    private String nome;
}