package com.algaworks.api.algafood.api.exceptionHandler;

import java.time.LocalDateTime;

public class Problema {
    private LocalDateTime dataHora;
    private String mensagem;

    public Problema() {
    }

    public Problema(LocalDateTime dataHora, String mensagem) {
        this.dataHora = dataHora;
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Problema setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
        return this;
    }

    public Problema setMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }

    public static Problema builder() {
        return new Problema();
    }

    public Problema build() {
        return this;
    }

    @Override
    public String toString() {
        return "Problema{" +
                "dataHora=" + dataHora +
                ", mensagem='" + mensagem + '\'' +
                '}';
    }
}