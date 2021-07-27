package com.fcamara.desafiobackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.ManyToOne;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    public Telefone() {

    }

    public Telefone(String numero) {
        this.numero = numero;
    }


}
