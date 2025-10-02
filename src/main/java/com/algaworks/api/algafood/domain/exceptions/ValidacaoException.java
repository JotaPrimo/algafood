package com.algaworks.api.algafood.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
@AllArgsConstructor
public class ValidacaoException extends RuntimeException {
    private BindingResult bindingResult;
}
