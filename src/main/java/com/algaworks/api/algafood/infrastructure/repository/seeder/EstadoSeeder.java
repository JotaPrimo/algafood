package com.algaworks.api.algafood.infrastructure.repository.seeder;

import com.algaworks.api.algafood.domain.model.Estado;
import com.algaworks.api.algafood.domain.repository.EstadoRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class EstadoSeeder {

    @Autowired
    private EstadoRepository estadoRepository;

    private final Faker faker = new Faker();


    @ShellMethod(key = "estadoSeeder seed", value = "Executando seed de estados")
    public void seed(
            @ShellOption(defaultValue = "5") int qtde) {
        List<Estado> estados = new ArrayList<>();
        for (int i = 0; i < qtde; i++) {
            Estado estado = new Estado();
            estado.setNome(faker.address().state());
            estados.add(estado);
        }
        estadoRepository.saveAll(estados);
        System.out.println("Seed de estados rodado!");
    }
}
