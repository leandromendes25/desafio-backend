package com.fcamara.desafiobackend.controller.dto;

import com.fcamara.desafiobackend.model.TipoVeiculo;
import com.fcamara.desafiobackend.model.Veiculo;

import java.util.List;
import java.util.stream.Collectors;

public class VeiculoDto {
    private Long id;
    private String marca;
    private String modelo;
    private String placa;
    private TipoVeiculo tipo;

    public VeiculoDto(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.modelo = veiculo.getModelo();
        this.placa = veiculo.getPlaca();
        this.tipo = veiculo.getTipo();
        this.marca = veiculo.getMarca();
    }
    public static List<VeiculoDto> converter(List<Veiculo> veiculos){
        return veiculos.stream().map(VeiculoDto::new).collect(Collectors.toList());
    }
    public static VeiculoDto converter(Veiculo veiculo){
        return new VeiculoDto(veiculo);
    }
}
