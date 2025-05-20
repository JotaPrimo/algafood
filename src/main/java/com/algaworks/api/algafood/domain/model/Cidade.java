package com.algaworks.api.algafood.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name = "cidades")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private Estado estado;
}
