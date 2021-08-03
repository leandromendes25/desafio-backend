package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.model.Estabelecimento;
import com.fcamara.desafiobackend.model.Veiculo;
import com.fcamara.desafiobackend.repository.EstabelecimentoRepository;
import com.fcamara.desafiobackend.repository.VeiculoRepository;
import com.fcamara.desafiobackend.service.ControleEntradaSaidaService;
import com.fcamara.desafiobackend.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/controle")
public class EntradaSaidaController {
    @Autowired
    private ControleEntradaSaidaService service;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @PutMapping("/entrada/{veiculoId}/{estabelecimentoId}")
    public ResponseEntity<?> entrada(@PathVariable Long veiculoId, @PathVariable Long estabelecimentoId){
        Optional<Estabelecimento> estabelecimentoDb = estabelecimentoRepository.findById(estabelecimentoId);
        Optional<Veiculo> veiculoDb = veiculoRepository.findById(veiculoId);
        if(!estabelecimentoDb.isPresent() || !veiculoDb.isPresent()){
            return ResponseEntity.badRequest().body(JsonResponse.message("Veiculo ou Estabelecimento Incorreto"));
        }
        return service.controleEntrada(veiculoDb.get(), estabelecimentoDb.get());
    }

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
