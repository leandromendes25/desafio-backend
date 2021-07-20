package model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Cpf cpf;
    private Endereco endereco;
    private Telefone telefone;
    private String vagasMotos;
    private String vagasCarros;

}
