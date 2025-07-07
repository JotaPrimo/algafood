package com.algaworks.api.algafood.infrastructure.repository.seeder.config;

public interface Seeder {
    void seed(int quantidade);

    /**
     * Nome usado para identificar cada seeder por nome no  momento da execução
     * */
    String getNome();
}
