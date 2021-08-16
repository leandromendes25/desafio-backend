package com.fcamara.desafiobackend.controller.dto;

import com.fcamara.desafiobackend.model.TipoVeiculo;
import com.fcamara.desafiobackend.model.Veiculo;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class VeiculoDto {

  private Long id;
  private String marca;
  private String modelo;
  private String cor;
  private String placa;
  private TipoVeiculo tipo;
  private boolean estacionado;

  public VeiculoDto(Veiculo veiculo) {
    this.id = veiculo.getId();
    this.marca = veiculo.getMarca();
    this.modelo = veiculo.getModelo();
    this.cor = veiculo.getCor();
    this.placa = veiculo.getPlaca();
    this.tipo = veiculo.getTipo();
    this.estacionado = veiculo.isEstacionado();
  }

  public static List<VeiculoDto> converter(List<Veiculo> veiculos) {
    return veiculos.stream().map(VeiculoDto::new).collect(Collectors.toList());
  }

  public static VeiculoDto converter(Veiculo veiculo) {
    return new VeiculoDto(veiculo);
  }
}
