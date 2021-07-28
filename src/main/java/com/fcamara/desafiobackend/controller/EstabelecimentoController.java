package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.controller.dto.EstabelecimentoDto;
import com.fcamara.desafiobackend.controller.form.EstabelecimentoForm;
import com.fcamara.desafiobackend.model.Estabelecimento;
import com.fcamara.desafiobackend.repository.EstabelecimentoRepository;
import com.fcamara.desafiobackend.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

  @Autowired
  private EstabelecimentoRepository repository;

  @GetMapping
  public ResponseEntity<List<EstabelecimentoDto>> getAll() {
    return ResponseEntity.ok(EstabelecimentoDto.converter(repository.findAll()));
  }

  @PostMapping
  public ResponseEntity<?> insert(@RequestBody @Valid EstabelecimentoForm form) {
    Optional<Estabelecimento> estabelecimentoDb = repository.findByCnpj(form.getCnpj());
    if(estabelecimentoDb.isPresent()) {
      return ResponseEntity.badRequest().body(JsonResponse.message("Estabelecimento já cadastrado!"));
    }
    return ResponseEntity.status(201).body(EstabelecimentoDto.converter(repository.save(form.converter())));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody @Valid EstabelecimentoForm form, @PathVariable Long id) {
    Optional<Estabelecimento> estabelecimentoDb = repository.findById(id);
    if(!estabelecimentoDb.isPresent()) return ResponseEntity.badRequest().body(JsonResponse.message("Estabelecimento nao encontrado"));
    Estabelecimento estabelecimento = form.converter(estabelecimentoDb.get());
    return ResponseEntity.ok(EstabelecimentoDto.converter(repository.save(estabelecimento)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> destroy(@PathVariable Long id) {
    Optional<Estabelecimento> estabelecimento = repository.findById(id);
    if (!estabelecimento.isPresent()) return ResponseEntity.badRequest().body(JsonResponse.message("Estabelecimento nao encontrado"));
    repository.delete(estabelecimento.get());
    return ResponseEntity.ok(JsonResponse.message("Estabelecimento excluido com sucesso"));
  }
}
