package com.fcamara.desafiobackend.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private TipoVeiculo tipo;

    public Veiculo() {

    }

    public Veiculo(String marca, String modelo, String cor, String placa, TipoVeiculo tipo) {
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.tipo = tipo;
    }

}
