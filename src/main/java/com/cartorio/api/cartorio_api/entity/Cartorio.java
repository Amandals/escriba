package com.cartorio.api.cartorio_api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "cartorio")
public class Cartorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "situacao_cartorio", referencedColumnName = "id")
    private SituacaoCartorio situacaoCartorio;

    @ManyToMany
    @JoinTable(
            name = "cartorio_atribuicoes",
            joinColumns = @JoinColumn(name = "cartorio_id"),
            inverseJoinColumns = @JoinColumn(name = "atribuicao_id")
    )
    private List<AtribuicaoCartorio> atribuicoes;
}
