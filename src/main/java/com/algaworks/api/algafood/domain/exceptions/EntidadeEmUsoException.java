package com.algaworks.api.algafood.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Entidade em uso. Dependência de relacionamentos")
public class EntidadeEmUsoException extends RuntimeException {
    public EntidadeEmUsoException(String message) {
        super(message);
    }
}
