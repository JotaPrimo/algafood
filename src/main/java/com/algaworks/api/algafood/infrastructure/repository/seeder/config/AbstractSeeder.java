package com.algaworks.api.algafood.infrastructure.repository.seeder.config;

import com.github.javafaker.Faker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class AbstractSeeder<T> implements Seeder {

    protected final Faker faker = new Faker(new Locale("pt", "BR"));
    private final JpaRepository<T, ?> repository;

    public AbstractSeeder(JpaRepository<T, ?> repository) {
        this.repository = repository;
    }

    @Override
    public void seed(int quantidade) {
        List<T> entidades = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            entidades.add(gerarEntidade());
        }
        repository.saveAll(entidades);
        System.out.println("Seed de " + getNome() + " processado com sucesso!");
    }

    protected abstract T gerarEntidade();
}