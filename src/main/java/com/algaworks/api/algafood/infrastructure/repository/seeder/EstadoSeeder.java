package com.algaworks.api.algafood.infrastructure.repository.seeder;

import com.algaworks.api.algafood.domain.model.Estado;
import com.algaworks.api.algafood.domain.repository.EstadoRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EstadoSeeder {

    @Autowired
    private EstadoRepository estadoRepository;

    private final Faker faker = new Faker();

    public void seed(int quantidade) {
        List<Estado> estados = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            Estado estado = new Estado();
            estado.setNome(faker.address().state());
            estados.add(estado);
        }
        estadoRepository.saveAll(estados);
        System.out.println("Seed de estados executado !");
    }
}
