package com.algaworks.api.algafood.infrastructure.repository.seeder.config;

public interface Seeder {
    void seed(int quantidade);
    String getNome(); // para identificar cada seeder por nome
}
