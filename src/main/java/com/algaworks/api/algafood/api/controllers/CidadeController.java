package com.algaworks.api.algafood.api.controllers;


import com.algaworks.api.algafood.api.assembler.CidadeInputDissasembler;
import com.algaworks.api.algafood.api.assembler.CidadeModelAssembler;
import com.algaworks.api.algafood.api.model.CidadeModel;
import com.algaworks.api.algafood.api.model.input.CidadeInput;
import com.algaworks.api.algafood.domain.exceptions.CidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.exceptions.EstadoNaoEncontradoException;
import com.algaworks.api.algafood.domain.model.Cidade;
import com.algaworks.api.algafood.domain.repository.CidadeRepository;
import com.algaworks.api.algafood.domain.service.CadastroCidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    private final CidadeRepository cidadeRepository;

    private final CadastroCidadeService cadastroCidade;
    private final CidadeModelAssembler assembler;
    private final CidadeInputDissasembler dissasembler;

    public CidadeController(CidadeRepository cidadeRepository, CadastroCidadeService cadastroCidade, CidadeModelAssembler assembler, CidadeInputDissasembler dissasembler) {
        this.cidadeRepository = cidadeRepository;
        this.cadastroCidade = cadastroCidade;
        this.assembler = assembler;
        this.dissasembler = dissasembler;
    }


    @GetMapping
    public List<CidadeModel> listar() {
        return assembler.toCollectionModel(cidadeRepository.findAll());
    }

    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        return assembler.toModel(cadastroCidade.buscarOuFalhar(cidadeId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = dissasembler.toModel(cidadeInput);
            return assembler.toModel(cadastroCidade.salvar(cidade));
        } catch (EntidadeNaoEncontradaException e) {
            throw new CidadeNaoEncontradaException(e.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@PathVariable Long cidadeId,
                            @RequestBody CidadeInput cidadeInput) {
        Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
        dissasembler.copyToDomainObject(cidadeInput, cidadeAtual);

        try {
            return assembler.toModel(cadastroCidade.salvar(cidadeAtual));
        } catch (EstadoNaoEncontradoException e) {
            throw new CidadeNaoEncontradaException(e.getMessage());
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidade.excluir(cidadeId);
    }
}