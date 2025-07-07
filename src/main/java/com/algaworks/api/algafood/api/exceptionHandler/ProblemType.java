package com.algaworks.api.algafood.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parametro informado é inválido"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "O recurso %s, que você tentou acessar, é inexistente."),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}