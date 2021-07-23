package com.fcamara.desafiobackend.controller.dto;

import com.fcamara.desafiobackend.model.Estabelecimento;

import java.util.List;
import java.util.stream.Collectors;

public class EstabelecimentoDto {

    private String cnpj;
    private String nome;
    private String qtdVagasCarros;
    private String qtdVagasMotos;

    public EstabelecimentoDto(Estabelecimento estabelecimento){
        this.cnpj = estabelecimento.getCnpj();
        this.nome = estabelecimento.getNome();
        this.qtdVagasCarros = estabelecimento.getVagasCarros();
        this.qtdVagasMotos = estabelecimento.getVagasMotos();
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getQtdVagasCarros() {
        return qtdVagasCarros;
    }

    public String getQtdVagasMotos() {
        return qtdVagasMotos;
    }
    public static List<EstabelecimentoDto> converter(List<Estabelecimento> estabelecimentos){
        return estabelecimentos.stream().map(EstabelecimentoDto::new).collect(Collectors.toList());
    }
}
