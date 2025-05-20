package com.algaworks.api.algafood.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private boolean ativo;

    @ManyToOne
    private Restaurante restaurante;
}
