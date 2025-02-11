package com.algaworks.api.algafood.domain.services;

import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CozinhaService {

    private final CozinhaRepository cozinhaRepository;

    public Cozinha buscar(Long id) {
        Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(id);

        if (cozinhaOptional.isPresent()) {
            return cozinhaOptional.get();
        }

        throw new RuntimeException("Registro não encontrado");
    }

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }
}