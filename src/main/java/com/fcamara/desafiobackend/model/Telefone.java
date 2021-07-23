package com.fcamara.desafiobackend.model;

import lombok.Getter;
import javax.persistence.ManyToOne;

import javax.persistence.*;

@Entity
@Getter
public class Telefone {

    @Id
    private String numero;
    @ManyToOne
    @JoinColumn(name = "estacionamento_id")
    private Estabelecimento estabelecimento;
}
