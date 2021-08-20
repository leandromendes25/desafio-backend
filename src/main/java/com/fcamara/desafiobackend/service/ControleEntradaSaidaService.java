package com.fcamara.desafiobackend.service;

import com.fcamara.desafiobackend.model.Estabelecimento;
import com.fcamara.desafiobackend.model.TipoVeiculo;
import com.fcamara.desafiobackend.model.Veiculo;
import com.fcamara.desafiobackend.repository.EstabelecimentoRepository;
import com.fcamara.desafiobackend.repository.VeiculoRepository;
import com.fcamara.desafiobackend.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class ControleEntradaSaidaService {
    @Autowired
    private VeiculoRepository veiculorepository;
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    //Necessário controle -> serviço
    public ResponseEntity<?>controleEntrada(@org.jetbrains.annotations.NotNull Veiculo veiculo, Estabelecimento estabelecimento){
        //estacionado
        if(veiculo.isEstacionado()){
            return ResponseEntity.badRequest().body(JsonResponse.message("Veiculo já está estacionado"));
        }   //Tipo Veiculo
        if (veiculo.getTipo() == TipoVeiculo.CARRO && estabelecimento.getVagasCarros() > estabelecimento.getVagasOcupadasCarros()){
        estabelecimento.setVagasOcupadasCarros(+1);
        veiculo.setEstacionado(true);
        veiculo.setEntrada(LocalTime.now());
        veiculorepository.save(veiculo);
        return ResponseEntity.ok().body(JsonResponse.entradaMensagem("Veiculo estacionado com sucesso", veiculo));
        }
        if (veiculo.getTipo() == TipoVeiculo.MOTO && estabelecimento.getVagasMotos() > estabelecimento.getVagasOcupadasMotos()) {
            estabelecimento.setVagasOcupadasMotos(+1);
            veiculo.setEstacionado(true);
            veiculo.setEntrada(LocalTime.now());
            veiculorepository.save(veiculo);//necessário salvar -> banco
            estabelecimentoRepository.save(estabelecimento);
            return ResponseEntity.ok().body(JsonResponse.entradaMensagem("Veiculo estacionado com sucesso", veiculo));
        }
        else return ResponseEntity.badRequest().body(JsonResponse.message("Erro ao dar entrada"));

    }

    public ResponseEntity<?> controleSaida(Veiculo veiculo, Estabelecimento estabelecimento){
        if (veiculo.isEstacionado() ){
            if(veiculo.getTipo().equals(TipoVeiculo.CARRO)){
                estabelecimento.setVagasOcupadasCarros(-1);
            }else estabelecimento.setVagasOcupadasMotos(-1);
            veiculo.setEstacionado(false);
            veiculorepository.save(veiculo);
            long tempoEstacionado = veiculo.getEntrada().until(LocalTime.now(), ChronoUnit.HOURS);
            double valor = estabelecimento.getValorHora();
            if(tempoEstacionado > 0) {
                valor = (tempoEstacionado * estabelecimento.getValorHora());
            }
            return ResponseEntity.ok().body(JsonResponse.retiradoMensagem("Veiculo retirado com sucesso", veiculo, valor));
    }
        else return ResponseEntity.badRequest().body(JsonResponse.message("Veiculo já retirado"));
    }
}
