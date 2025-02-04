package com.algaworks.api.algafood.jpa;


import com.algaworks.api.algafood.AlgafoodApiApplication;
import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;


public class ConsultaCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);

        List<Cozinha> cozinhas = cadastroCozinha.todas();

        for (Cozinha cozinha : cozinhas) {
            System.out.println(cozinha.getNome());
        }

        System.out.println("=========================");
         Cozinha cozinha = cadastroCozinha.porId(1L);
        System.out.println("Cozinha encontrada ");
        System.out.println(cozinha.getNome());
    }
}
