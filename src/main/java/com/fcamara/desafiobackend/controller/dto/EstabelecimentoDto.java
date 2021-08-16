package com.fcamara.desafiobackend.controller.dto;

import com.fcamara.desafiobackend.model.Estabelecimento;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EstabelecimentoDto {

    private Long id;
    private String nome;
    private String cnpj;
    private List<EnderecoDto> enderecos;
    private List<TelefoneDto> telefones;
    private Integer quantidadeVagasMotos;
    private Integer quantidadeVagasCarros;

    public EstabelecimentoDto(Estabelecimento estabelecimento, List<EnderecoDto> enderecos, List<TelefoneDto> telefones) {
        this.id = estabelecimento.getId();
        this.cnpj = estabelecimento.getCnpj();
        this.nome = estabelecimento.getNome();
        this.enderecos = enderecos;
        this.telefones = telefones;
        this.quantidadeVagasMotos = estabelecimento.getVagasMotos();
        this.quantidadeVagasCarros = estabelecimento.getVagasCarros();
    }

    public static List<EstabelecimentoDto> converter(List<Estabelecimento> estabelecimentos){
        return estabelecimentos.stream().map(
                estabelecimento -> new EstabelecimentoDto(estabelecimento, EnderecoDto.converter(estabelecimento.getEnderecos()), TelefoneDto.converter(estabelecimento.getTelefones()))
        ).collect(Collectors.toList());
    }

    public static EstabelecimentoDto converter(Estabelecimento estabelecimento) {
        return new EstabelecimentoDto(estabelecimento, EnderecoDto.converter(estabelecimento.getEnderecos()), TelefoneDto.converter(estabelecimento.getTelefones()));
    }
}
