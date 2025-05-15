package com.algaworks.api.algafood.infrastructure.repository.seeder;

import com.algaworks.api.algafood.domain.model.Cidade;
import com.algaworks.api.algafood.domain.model.Estado;
import com.algaworks.api.algafood.domain.repository.EstadoRepository;
import com.algaworks.api.algafood.infrastructure.repository.seeder.config.AbstractSeeder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class CidadeSeeder extends AbstractSeeder<Cidade> {

    private final EstadoRepository estadoRepository;

    public CidadeSeeder(JpaRepository<Cidade, ?> repository, EstadoRepository estadoRepository) {
        super(repository);
        this.estadoRepository = estadoRepository;
    }

    @Override
    protected Cidade gerarEntidade() {
        Estado estado = retornaRegistroAleatorio();
        Cidade cidade = new Cidade();
        cidade.setNome(faker.address().cityName());
        cidade.setEstado(estado);

        return cidade;
    }

    private Estado retornaRegistroAleatorio() {
        List<Estado> estados = estadoRepository.findTop20By();

        if (estados.isEmpty()) {
            throw new IllegalStateException("Nenhum estado encontrado");
        }

        int index = ThreadLocalRandom.current().nextInt(estados.size());

        return estados.get(index);
    }

    @Override
    public String getNome() {
        return "cidade";
    }
}