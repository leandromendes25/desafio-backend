package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.controller.dto.VeiculoDto;
import com.fcamara.desafiobackend.controller.form.VeiculoForm;
import com.fcamara.desafiobackend.model.Veiculo;
import com.fcamara.desafiobackend.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fcamara.desafiobackend.repository.VeiculoRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> insert(@RequestBody @Valid VeiculoForm form){
        Optional<Veiculo> veiculoDb = repository.findByPlaca(form.getPlaca());
        if(veiculoDb.isPresent()) {
            return ResponseEntity.badRequest().body(JsonResponse.message("Veiculo ja cadastrado"));
        }
       return ResponseEntity.status(201).body(VeiculoDto.converter(repository.save(form.converter())));
    }

}
