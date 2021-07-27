package com.fcamara.desafiobackend.controller.form;

import com.fcamara.desafiobackend.model.Endereco;
import com.fcamara.desafiobackend.model.Estabelecimento;
import com.fcamara.desafiobackend.model.Telefone;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EstabelecimentoForm {

  @NotNull
  private String nome;
  @NotNull
  private String cnpj;
  @NotNull
  private Endereco endereco;
  @NotNull
  private Telefone telefone;
  @NotNull
  private Integer vagasMotos;
  @NotNull
  private Integer vagasCarros;

  public Estabelecimento converter() {
    Estabelecimento estabelecimento = new Estabelecimento(nome, cnpj, vagasMotos, vagasCarros);
    estabelecimento.adicionarEndereco(endereco);
    estabelecimento.adicionarTelefone(telefone);
    return estabelecimento;
  }
}
