package com.cartorio.api.cartorio_api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "atribuicao_cartorio")
public class AtribuicaoCartorio {

    @Id
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "situacao", nullable = false)
    private Boolean situacao;
}

