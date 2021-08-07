package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.model.Estabelecimento;
import com.fcamara.desafiobackend.model.Veiculo;
import com.fcamara.desafiobackend.repository.EstabelecimentoRepository;
import com.fcamara.desafiobackend.repository.VeiculoRepository;
import com.fcamara.desafiobackend.service.ControleEntradaSaidaService;
import com.fcamara.desafiobackend.util.JsonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Tag(name="Controle de Entrada e Saida de Veiculos")
@RequestMapping("/controle")
public class EntradaSaidaController {
    @Autowired
    private ControleEntradaSaidaService service;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;
    @Operation(summary = "Registra entrada do veiculo nos estabelecimentos")
    @ApiResponse(responseCode = "200", description = "ENTRADA REGISTRADA COM SUCESSO")
    @ApiResponse(responseCode = "400", description = "FALHA AO REGISTRAR  ENTRADA")
    @PutMapping("/entrada/{veiculoId}/{estabelecimentoId}")
    public ResponseEntity<?> entrada(@PathVariable Long veiculoId, @PathVariable Long estabelecimentoId){
        Optional<Estabelecimento> estabelecimentoDb = estabelecimentoRepository.findById(estabelecimentoId);
        Optional<Veiculo> veiculoDb = veiculoRepository.findById(veiculoId);
        if(!estabelecimentoDb.isPresent() || !veiculoDb.isPresent()){
            return ResponseEntity.badRequest().body(JsonResponse.message("Veiculo ou Estabelecimento Incorreto"));
        }
        return service.controleEntrada(veiculoDb.get(), estabelecimentoDb.get());
    }
    @Operation(summary = "Registra saida do veiculo nos estabelecimentos")
    @ApiResponse(responseCode = "200", description = "SAIDA REGISTRADA COM SUCESSO")
    @ApiResponse(responseCode = "400", description = "FALHA AO RETIRAR VEICULO, VEICULO JÁ RETIRADO")
    @PutMapping("/saida/{veiculoId}/{estabelecimentoId}")
    public ResponseEntity<?> saida(@PathVariable Long veiculoId, @PathVariable Long estabelecimentoId){
        Optional<Estabelecimento> estabelecimentoDb = estabelecimentoRepository.findById(estabelecimentoId);
        Optional<Veiculo> veiculoDb = veiculoRepository.findById(veiculoId);
        if(!estabelecimentoDb.isPresent() || !veiculoDb.isPresent()){
            return ResponseEntity.badRequest().body(JsonResponse.message("Veiculo ou Estabelecimento Incorreto"));
        }
        return service.controleSaida(veiculoDb.get(), estabelecimentoDb.get());
}
}
