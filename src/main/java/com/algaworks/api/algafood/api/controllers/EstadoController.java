package com.algaworks.api.algafood.api.controllers;

import com.algaworks.api.algafood.api.assembler.EstadoInputlDissassembler;
import com.algaworks.api.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.api.algafood.api.model.EstadoModel;
import com.algaworks.api.algafood.api.model.input.EstadoInput;
import com.algaworks.api.algafood.domain.model.Estado;
import com.algaworks.api.algafood.domain.repository.EstadoRepository;
import com.algaworks.api.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoRepository estadoRepository;
    private final CadastroEstadoService cadastroEstado;

    private final EstadoModelAssembler estadoModelAssembler;
    private final EstadoInputlDissassembler estadoInputlDissassembler;

    public EstadoController(EstadoRepository estadoRepository, CadastroEstadoService cadastroEstado, EstadoModelAssembler estadoModelAssembler, EstadoInputlDissassembler estadoInputlDissassembler) {
        this.estadoRepository = estadoRepository;
        this.cadastroEstado = cadastroEstado;
        this.estadoModelAssembler = estadoModelAssembler;
        this.estadoInputlDissassembler = estadoInputlDissassembler;
    }

    @GetMapping
    public List<EstadoModel> listar() {
        return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
    }

    @GetMapping("/{estadoId}")
    public EstadoModel buscar(@PathVariable Long estadoId) {
        return estadoModelAssembler.toModel(cadastroEstado.buscarOuFalhar(estadoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody Estado estado) {
        return estadoModelAssembler.toModel(cadastroEstado.salvar(estado));
    }

    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable Long estadoId,
                            @RequestBody EstadoInput estadoInput) {
        Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);

        estadoInputlDissassembler.copyToDomainObject(estadoInput, estadoAtual);

        return estadoModelAssembler.toModel(cadastroEstado.salvar(estadoAtual));
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        cadastroEstado.excluir(estadoId);
    }

}