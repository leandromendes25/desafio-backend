package com.fcamara.desafiobackend.controller.form;

import com.fcamara.desafiobackend.model.Endereco;
import com.fcamara.desafiobackend.model.Estabelecimento;
import com.fcamara.desafiobackend.model.Telefone;
import com.fcamara.desafiobackend.model.Usuario;
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
  private EnderecoForm endereco;
  @NotNull
  private String telefone;
  @NotNull
  private Integer vagasMotos;
  @NotNull
  private Integer vagasCarros;
  @NotNull
  private double valorHora;

  public Estabelecimento converter(Usuario usuario) {
    Estabelecimento estabelecimento = new Estabelecimento(nome, cnpj, vagasMotos, vagasCarros, valorHora);
    Endereco endereco = this.endereco.converter();
    estabelecimento.adicionarEndereco(endereco);
    Telefone telefone = new Telefone(this.telefone);
    estabelecimento.adicionarTelefone(telefone);
    estabelecimento.adicionarUsuario(usuario);
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
