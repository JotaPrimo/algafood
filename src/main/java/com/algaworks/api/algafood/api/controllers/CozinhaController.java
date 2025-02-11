package com.algaworks.api.algafood.api.controllers;

import com.algaworks.api.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.domain.services.CozinhaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private final CozinhaService cozinhaService;

    @Autowired
    public CozinhaController(CozinhaService cozinhaService) {
        this.cozinhaService = cozinhaService;
    }

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Cozinha cozinha = cozinhaService.buscar(id);

        if (cozinha == null) {
            return ResponseEntity.notFound().build();
        }

        // status depois o body ou build se não quiser corpo
        return ResponseEntity.status(HttpStatus.OK).body(cozinha);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        List<Cozinha> cozinhas = cozinhaService.listar();
        return new CozinhasXmlWrapper(cozinhas);
    }

    @PostMapping
    public ResponseEntity<Cozinha> salvar(@RequestBody @Valid Cozinha cozinha) {
        Cozinha cozinhaCreated = cozinhaService.salvar(cozinha);

        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody @Valid Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaService.buscar(id);

        if (cozinha == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        cozinhaAtual = cozinhaService.salvar(cozinhaAtual);

        return ResponseEntity.status(HttpStatus.OK).body(cozinhaAtual);
    }
}