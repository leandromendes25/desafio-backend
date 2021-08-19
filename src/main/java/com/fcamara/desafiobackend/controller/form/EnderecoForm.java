package com.fcamara.desafiobackend.controller.form;

import com.fcamara.desafiobackend.model.Endereco;
import com.fcamara.desafiobackend.model.Estabelecimento;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
public class EnderecoForm {

    @NotNull
    private String estado;
    @NotNull
    private String cidade;
    @NotNull
    private String bairro;
    @NotNull
    private String cep;
    @NotNull
    private String rua;
    @NotNull
    private int numero;
    @NotNull
    private String complemento = "N/A";

    public Endereco converter(){
    return new Endereco(estado,cidade,bairro,cep,rua,numero,complemento);

    }
}
