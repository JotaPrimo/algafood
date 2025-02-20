package com.algaworks.api.algafood.api.controllers;


import com.algaworks.api.algafood.api.exceptions.EntidadeEmUsoException;
import com.algaworks.api.algafood.api.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.model.Cidade;
import com.algaworks.api.algafood.domain.service.CadastroCidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CadastroCidadeService cidadeService;

    @Autowired
    public CidadeController(CadastroCidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public List<Cidade> listar() {
        return cidadeService.buscarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long id) {
        try {
            Cidade estado = cidadeService.buscarPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(estado);
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Cidade> cadastrar(@RequestBody @Valid Cidade cidade) {
        Cidade cidadeSaved = cidadeService.salvar(cidade);

        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long id, @RequestBody @Valid Cidade cidade) {
        try {
            Cidade cidadeSaved = cidadeService.buscarPorId(id);

            BeanUtils.copyProperties(cidade, cidadeSaved, "id");

            return ResponseEntity.status(HttpStatus.OK).body(cidadeSaved);
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            cidadeService.remover(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (EntidadeEmUsoException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
