package com.cartorio.api.cartorio_api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "situacao_cartorio")
public class SituacaoCartorio {

    @Id
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

}
