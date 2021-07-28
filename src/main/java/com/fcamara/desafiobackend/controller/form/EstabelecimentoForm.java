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
  @NotNull
  private double valorHora;

  public Estabelecimento converter() {
    Estabelecimento estabelecimento = new Estabelecimento(nome, cnpj, vagasMotos, vagasCarros, valorHora);
    estabelecimento.adicionarEndereco(endereco);
    estabelecimento.adicionarTelefone(telefone);
    return estabelecimento;
  }

  public Estabelecimento converter(Estabelecimento estabelecimentoDb) {
    Estabelecimento estabelecimento = new Estabelecimento(nome, cnpj, vagasMotos, vagasCarros, valorHora);
    estabelecimento.setEnderecos(estabelecimentoDb.getEnderecos());
    estabelecimento.setTelefones(estabelecimentoDb.getTelefones());
    estabelecimento.setId(estabelecimentoDb.getId());
    return estabelecimento;
  }
}
