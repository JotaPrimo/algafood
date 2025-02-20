package com.algaworks.api.algafood.api.controllers;

import com.algaworks.api.algafood.api.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.model.Restaurante;
import com.algaworks.api.algafood.domain.service.CadastroRestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    public RestauranteController(CadastroRestauranteService cadastroRestauranteService) {
        this.cadastroRestauranteService = cadastroRestauranteService;
    }

    @GetMapping
    public List<Restaurante> listar() {
        return cadastroRestauranteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        Restaurante restaurante = cadastroRestauranteService.buscarPorId(id);

        if (restaurante == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(restaurante);
    }

    @PostMapping
    public ResponseEntity<Restaurante> salvar(@RequestBody @Valid Restaurante restaurante) {
        Restaurante restauranteAtual = cadastroRestauranteService.salvar(restaurante);

        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteAtual);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long id, @RequestBody @Valid Restaurante restaurante) {
        Restaurante restauranteAtual = cadastroRestauranteService.buscarPorId(id);

        if (restauranteAtual == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
        Restaurante restauranteUpdated = cadastroRestauranteService.salvar(restauranteAtual);

        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteUpdated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody @Valid Map<String, Object> campos) {

        Restaurante restauranteAtual = cadastroRestauranteService.buscarPorId(id);

        if (restauranteAtual == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //todo atualizar propriedades
        merge(campos, restauranteAtual);

        return atualizar(id, restauranteAtual);
    }

    private static void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
        camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            System.out.println("==========================================");
            System.out.println(nomePropriedade + " = " + valorPropriedade);
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            cadastroRestauranteService.remover(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}