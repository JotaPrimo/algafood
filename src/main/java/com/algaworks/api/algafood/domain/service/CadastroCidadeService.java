package com.algaworks.api.algafood.domain.service;

import com.algaworks.api.algafood.domain.exceptions.CidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.api.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.api.algafood.domain.model.Cidade;
import com.algaworks.api.algafood.domain.model.Estado;
import com.algaworks.api.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCidadeService {

    private static final String MSG_CIDADE_EM_USO
            = "Cidade de código %d não pode ser removida, pois está em uso";

    private static final String MSG_CIDADE_NAO_ENCONTRADA
            = "Não existe um cadastro de cidade com código %d";

    private final CidadeRepository cidadeRepository;

    private final CadastroEstadoService cadastroEstado;

    public CadastroCidadeService(CidadeRepository cidadeRepository, CadastroEstadoService cadastroEstado) {
        this.cidadeRepository = cidadeRepository;
        this.cadastroEstado = cadastroEstado;
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstado.buscarOuFalhar(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
            cidadeRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(cidadeId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO, cidadeId));
        }
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
    }
}