package com.algaworks.api.algafood.api.controllers;

import com.algaworks.api.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.OK)
    public Cozinha buscar(@PathVariable Long id) {
        Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(id);

        if (cozinhaOptional.isPresent()) {
            return cozinhaOptional.get();
        }

        throw new RuntimeException("Registro não encontrado");
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        List<Cozinha> cozinhas = cozinhaRepository.findAll();
        return new CozinhasXmlWrapper(cozinhas);
    }
}