package com.fcamara.desafiobackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();
    @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL)
    private List<Telefone> telefones = new ArrayList<>();
    private Integer vagasMotos;
    private Integer vagasCarros;
    private Integer vagasOcupadasMotos;
    private Integer vagasOcupadasCarros;
    private double valorHora;

    public Estabelecimento() {

    }

    public Estabelecimento(String nome, String cnpj, Integer vagasMotos, Integer vagasCarros) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.vagasMotos = vagasMotos;
        this.vagasCarros = vagasCarros;
    }

    public void adicionarEndereco(Endereco endereco) {
      endereco.setEstabelecimento(this);
      this.enderecos.add(endereco);
    }

    public void adicionarTelefone(Telefone telefone) {
      telefone.setEstabelecimento(this);
      this.telefones.add(telefone);
    }
}
