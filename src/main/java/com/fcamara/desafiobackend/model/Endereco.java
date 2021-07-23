package com.fcamara.desafiobackend.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class Endereco {
    @Id
    private String cep;
    private String rua;
    private String numero;
    @ManyToOne
    @JoinColumn(name = "estacionamento_id")
    private Estabelecimento estabelecimento;

}
