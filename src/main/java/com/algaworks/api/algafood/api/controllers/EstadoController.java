package com.algaworks.api.algafood.api.controllers;

import com.algaworks.api.algafood.api.exceptions.EntidadeEmUsoException;
import com.algaworks.api.algafood.api.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.model.Estado;
import com.algaworks.api.algafood.domain.service.EstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoService estadoService;

    @Autowired
    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<Estado> listar() {
        return estadoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscar(@PathVariable Long id) {
        try {
            Estado estado = estadoService.buscarPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(estado);
        }catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Estado> cadastrar(@RequestBody @Valid Estado estado) {
        Estado estadoSaved = estadoService.salvar(estado);

        return ResponseEntity.status(HttpStatus.CREATED).body(estadoSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody @Valid Estado estado) {
        try {
            Estado estadoSaved = estadoService.buscarPorId(id);

            BeanUtils.copyProperties(estado, estadoSaved, "id");

            return ResponseEntity.status(HttpStatus.OK).body(estadoSaved);
        }catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            estadoService.remover(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (EntidadeEmUsoException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
