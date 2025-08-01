package com.algaworks.api.algafood.domain.model;

import com.algaworks.api.algafood.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "restaurantes")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // @NotNull(message = "Nome não pode ser ser null")
    // @NotEmpty(message = "Nome não pode ser ser null")
    @NotBlank
    @Column(nullable = false)
    @Length(min = 5, max = 255)
    private String nome;

    @PositiveOrZero(message = "{TaxaFrete.invalida}")
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @Valid // isso valida em cascata, significa que vai dentro do objeto ver se seus dados estão ok
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @JsonIgnore
    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")
    )
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    public Restaurante() {
    }

    public Restaurante(Long id, String nome, BigDecimal taxaFrete, Cozinha cozinha, Endereco endereco, LocalDateTime dataCadastro, LocalDateTime dataAtualizacao, List<Produto> produtos, List<FormaPagamento> formasPagamento) {
        this.id = id;
        this.nome = nome;
        this.taxaFrete = taxaFrete;
        this.cozinha = cozinha;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
        this.produtos = produtos;
        this.formasPagamento = formasPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public Cozinha getCozinha() {
        return cozinha;
    }

    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }
}