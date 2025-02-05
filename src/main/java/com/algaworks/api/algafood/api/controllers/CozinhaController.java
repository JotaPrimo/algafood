package com.algaworks.api.algafood.api.controllers;

import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private final CozinhaRepository cozinhaRepository;

    @Autowired
    public CozinhaController(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cozinha buscar(@PathVariable Long id) {
        Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(id);

        if (cozinhaOptional.isPresent()) {
            return cozinhaOptional.get();
        }

        throw new RuntimeException("Registro não encontrado");
    }
}