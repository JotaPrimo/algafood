package com.algaworks.api.algafood.infrastructure.repository.seeder;

import com.algaworks.api.algafood.domain.model.Estado;
import com.algaworks.api.algafood.domain.repository.EstadoRepository;
import com.algaworks.api.algafood.infrastructure.repository.seeder.config.AbstractSeeder;
import org.springframework.stereotype.Component;

@Component
public class EstadoSeeder extends AbstractSeeder<Estado> {

    public EstadoSeeder(EstadoRepository repository) {
        super(repository);
    }

    @Override
    protected Estado gerarEntidade() {
        Estado estado = new Estado();
        estado.setNome(faker.address().state());
        return estado;
    }

    @Override
    public String getNome() {
        return "estado";
    }
}