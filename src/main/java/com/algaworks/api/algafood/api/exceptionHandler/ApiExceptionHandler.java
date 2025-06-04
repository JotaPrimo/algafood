package com.algaworks.api.algafood.api.exceptionHandler;

import com.algaworks.api.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.exceptions.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
            EntidadeNaoEncontradaException e) {
        Problema problema = Problema.builder()
                .setDataHora(LocalDateTime.now())
                .setMensagem(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problema);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e) {
        Problema problema = Problema.builder()
                .setDataHora(LocalDateTime.now())
                .setMensagem(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problema);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException() {
        Problema problema = Problema.builder()
                .setDataHora(LocalDateTime.now())
                .setMensagem("O tipo de mídia não é aceito.").build();

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(problema);
    }
}