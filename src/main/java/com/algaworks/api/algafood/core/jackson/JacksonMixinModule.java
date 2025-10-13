package com.algaworks.api.algafood.core.jackson;

import com.algaworks.api.algafood.api.model.mixin.CidadeMixin;
import com.algaworks.api.algafood.domain.model.Cidade;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {
    public JacksonMixinModule() {
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
    }
}