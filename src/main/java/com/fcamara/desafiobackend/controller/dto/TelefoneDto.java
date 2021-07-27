package com.fcamara.desafiobackend.controller.dto;

import com.fcamara.desafiobackend.model.Estabelecimento;
import com.fcamara.desafiobackend.model.Telefone;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
public class TelefoneDto {

  private String numero;

  public TelefoneDto(Telefone telefone) {
    this.numero = telefone.getNumero();
  }

  public static List<TelefoneDto> converter(List<Telefone> telefones) {
    return telefones.stream().map(TelefoneDto::new).collect(Collectors.toList());
  }
}
