package com.algaworks.api.algafood.api.controllers;

import com.algaworks.api.algafood.api.assembler.CozinhaInputDissasembler;
import com.algaworks.api.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.api.algafood.api.model.CozinhaModel;
import com.algaworks.api.algafood.api.model.input.CozinhaInput;
import com.algaworks.api.algafood.domain.model.Cozinha;
import com.algaworks.api.algafood.domain.repository.CozinhaRepository;
import com.algaworks.api.algafood.domain.service.CadastroCozinhaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    private final CozinhaRepository cozinhaRepository;

    private final CadastroCozinhaService cadastroCozinha;
    private final CozinhaModelAssembler assembler;
    private final CozinhaInputDissasembler dissasembler;

    public CozinhaController(CozinhaRepository cozinhaRepository, CadastroCozinhaService cadastroCozinha, CozinhaModelAssembler assembler, CozinhaInputDissasembler dissasembler) {
        this.cozinhaRepository = cozinhaRepository;
        this.cadastroCozinha = cadastroCozinha;
        this.assembler = assembler;
        this.dissasembler = dissasembler;
    }

    @GetMapping
    public List<CozinhaModel> listar() {
        return assembler.toCollectionModel(cozinhaRepository.findAll());
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Long cozinhaId) {
        return assembler.toModel(cadastroCozinha.buscarOuFalhar(cozinhaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = dissasembler.toModel(cozinhaInput);
        return assembler.toModel(cadastroCozinha.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Long cozinhaId,
                             @RequestBody CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);

        dissasembler.copyToDomainObject(cozinhaInput, cozinhaAtual);

        return assembler.toModel(cadastroCozinha.salvar(cozinhaAtual));
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinha.excluir(cozinhaId);
    }

}