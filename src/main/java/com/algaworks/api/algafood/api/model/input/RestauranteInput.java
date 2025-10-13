package com.algaworks.api.algafood.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestauranteInput {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private BigDecimal taxaFrete;

    @Valid // isso valida em cascata, significa que vai dentro do objeto ver se seus dados estão ok
    @NotNull
    private CozinhaIdInput cozinha;
}
