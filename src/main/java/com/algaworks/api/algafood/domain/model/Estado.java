package com.algaworks.api.algafood.domain.model;

import com.algaworks.api.algafood.Groups;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Table(name = "estados")
@Entity
public class Estado {

    @Id
    @EqualsAndHashCode.Include
    @NotNull(message = "Id do estado não pode ser null", groups = Groups.EstadoId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do estado é um campo obrigatório")
    @Column(nullable = false)
    private String nome;

    public Estado() {
    }

    public Estado(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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
}
