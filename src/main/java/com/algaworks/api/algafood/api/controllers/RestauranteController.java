package com.algaworks.api.algafood.api.controllers;

import com.algaworks.api.algafood.api.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.model.Restaurante;
import com.algaworks.api.algafood.domain.repository.RestauranteRepository;
import com.algaworks.api.algafood.domain.service.CadastroRestauranteService;
import com.algaworks.api.algafood.infrastructure.repository.spec.RestauranteSpecs;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final CadastroRestauranteService cadastroRestauranteService;
    private final RestauranteRepository restauranteRepository;

    @Autowired
    public RestauranteController(CadastroRestauranteService cadastroRestauranteService, RestauranteRepository restauranteRepository) {
        this.cadastroRestauranteService = cadastroRestauranteService;
        this.restauranteRepository = restauranteRepository;
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

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco");
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

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            cadastroRestauranteService.remover(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome) {
        return restauranteRepository.findAll(RestauranteSpecs.comFreteGratis().and(RestauranteSpecs.comNomeSemelhante(nome)));
    }
}