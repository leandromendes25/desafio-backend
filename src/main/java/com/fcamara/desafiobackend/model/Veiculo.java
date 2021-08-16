package com.fcamara.desafiobackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private TipoVeiculo tipo;
    private boolean estacionado = false;
    private LocalTime entrada;
    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }

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
