package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.controller.dto.VeiculoDto;
import com.fcamara.desafiobackend.controller.form.VeiculoForm;
import com.fcamara.desafiobackend.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fcamara.desafiobackend.repository.VeiculoRepository;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoRepository repository;

    @GetMapping
    public ResponseEntity<List<VeiculoDto>> getAll() {
        return  ResponseEntity.ok(VeiculoDto.converter(repository.findAll()));
    }

    @PostMapping
    public ResponseEntity<VeiculoDto> insert(VeiculoForm form, UriComponentsBuilder uriBuilder){//form pega e converte
       Veiculo veiculo = repository.save(form.converter());
       URI uri = uriBuilder.path("/veiculos/{id}").buildAndExpand(veiculo.getId()).toUri();
       return ResponseEntity.created(uri).body(new VeiculoDto(veiculo));
    }

}
