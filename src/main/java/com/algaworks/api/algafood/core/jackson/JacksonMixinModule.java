package com.algaworks.api.algafood.core.jackson;

import com.algaworks.api.algafood.api.model.mixin.RestauranteMixin;
import com.algaworks.api.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {
    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }
}