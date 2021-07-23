package com.fcamara.desafiobackend.controller.form;

import com.fcamara.desafiobackend.model.TipoVeiculo;
import com.fcamara.desafiobackend.model.Veiculo;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoForm {

    @NotNull
    private String marca;
    @NotNull
    private String modelo;
    @NotNull
    private String cor;
    @NotNull
    private String placa;
    @NotNull
    private TipoVeiculo tipo;

    public Veiculo converter(){
        return new Veiculo(marca, modelo, cor,placa,tipo);
    }
}
