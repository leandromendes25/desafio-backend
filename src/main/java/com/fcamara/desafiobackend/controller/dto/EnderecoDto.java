package com.fcamara.desafiobackend.controller.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fcamara.desafiobackend.model.Endereco;
import com.fcamara.desafiobackend.model.Estabelecimento;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EnderecoDto {

  private String estado;
  private String cidade;
  private String bairro;
  private String cep;
  private String rua;
  private Integer numero;
  private String complemento;

  public EnderecoDto(Endereco endereco) {
    this.estado = endereco.getEstado();
    this.cidade = endereco.getCidade();
    this.bairro = endereco.getBairro();
    this.cep = endereco.getCep();
    this.rua = endereco.getRua();
    this.numero = endereco.getNumero();
    this.complemento = endereco.getComplemento();
  }

  public static List<EnderecoDto> converter(List<Endereco> enderecos) {
    return enderecos.stream().map(EnderecoDto::new).collect(Collectors.toList());
  }
}
