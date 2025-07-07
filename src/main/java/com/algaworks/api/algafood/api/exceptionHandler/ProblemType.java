package com.algaworks.api.algafood.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parametro informado é inválido"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o admininstrador do sistema"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "O recurso %s, que você tentou acessar, é inexistente."),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}