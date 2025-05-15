package com.algaworks.api.algafood.infrastructure.repository.seeder;

import com.algaworks.api.algafood.infrastructure.repository.seeder.config.Seeder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class SeederExecutor {

    @PersistenceContext
    private EntityManager entityManager;

    private final List<Seeder> seeders;

    @Autowired
    public SeederExecutor(
            EstadoSeeder estadoSeeder,
            CidadeSeeder cidadeSeeder
    ) {
        this.seeders = Arrays.asList(
                estadoSeeder,
                cidadeSeeder
        );
    }

    public void seedAll(int quantidade) {
        seeders.forEach(seeder -> seeder.seed(quantidade));
    }

    public void seedByNome(String nome, int quantidade) {
        Optional<Seeder> optionalSeeder = seeders.stream()
                .filter(seeder -> seeder.getNome().equalsIgnoreCase(nome))
                .findFirst();

        if (optionalSeeder.isPresent()) {
            optionalSeeder.get().seed(quantidade);
        } else {
            System.out.println("Seeder com nome '" + nome + "' não encontrado.");
        }
    }

   @Transactional
    public void truncateTable(String nomeTabela) {
        // TODO: 15/05/2025 implementar lógica para verificar se nome da tabela foi enviado 
        entityManager
                .createNativeQuery("TRUNCATE TABLE " + nomeTabela + " RESTART IDENTITY CASCADE")
                .executeUpdate();
        System.out.println("Tabela '" + nomeTabela + "' limpa com sucesso!");
    }
}