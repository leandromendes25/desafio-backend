package com.fcamara.desafiobackend.model;

import lombok.Getter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToMany
    private List<Endereco> endereco = new ArrayList<>();
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToMany
    private List<Telefone> telefones = new ArrayList<>();
    private String vagasMotos;
    private String vagasCarros;
}
