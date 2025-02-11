package com.algaworks.api.algafood.domain.services;

import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.domain.repository.CozinhaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CozinhaService {

    private final CozinhaRepository cozinhaRepository;

    public Cozinha buscar(Long id) {
        Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(id);

        return cozinhaOptional.orElse(null);
    }

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        Objects.requireNonNull(cozinha, "Cozinha não pode ser null");
        return cozinhaRepository.save(cozinha);
    }
}