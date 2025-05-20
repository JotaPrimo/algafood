package com.algaworks.api.algafood.infrastructure.repository.seeder;

import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.infrastructure.repository.seeder.config.AbstractSeeder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class CozinhaSeeder extends AbstractSeeder<Cozinha> {

    public CozinhaSeeder(JpaRepository<Cozinha, ?> repository) {
        super(repository);
    }

    @Override
    public String getNome() {
        return "cozinha";
    }

    @Override
    protected Cozinha gerarEntidade() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(faker.country().name());
        return cozinha;
    }
}
