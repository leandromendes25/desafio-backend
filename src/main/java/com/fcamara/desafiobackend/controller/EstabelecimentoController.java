package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.config.security.TokenService;
import com.fcamara.desafiobackend.controller.dto.EstabelecimentoDto;
import com.fcamara.desafiobackend.controller.form.EstabelecimentoForm;
import com.fcamara.desafiobackend.model.Estabelecimento;
import com.fcamara.desafiobackend.model.Usuario;
import com.fcamara.desafiobackend.repository.EstabelecimentoRepository;
import com.fcamara.desafiobackend.repository.UsuarioRepository;
import com.fcamara.desafiobackend.util.JsonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name="Controle de Estabelecimentos")
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {
  @Autowired
  private TokenService tokenService;

  @Autowired
  private EstabelecimentoRepository repository;

  @Autowired
  private UsuarioRepository usuarioRepository;
  @Operation(summary = "Utilizado para listar os estabelecimentos")
  @ApiResponse(responseCode = "200", description = "ESTABELECIMENTOS LISTADOS COM SUCESSO")
  @ApiResponse(responseCode = "400", description = "FALHA AO LISTAR ESTABELECIMENTOS")
  @GetMapping
  public ResponseEntity<List<EstabelecimentoDto>> getAll() {
    return ResponseEntity.ok(EstabelecimentoDto.converter(repository.findAll()));
  }
  @Operation(summary = "Registra estabelecimentos", security = { @SecurityRequirement(name = "bearer-key")})
  @ApiResponse(responseCode = "200", description = "ESTABELECIMENTO REGISTRADO COM SUCESSO")
  @ApiResponse(responseCode = "400", description = "FALHA AO REGISTRAR ESTABELECIMENTO")
  @PostMapping
  public ResponseEntity<?> insert(@RequestHeader("Authorization") String token, @RequestBody @Valid EstabelecimentoForm form) {
    Long usuarioId = tokenService.getUsuarioId(token.substring(7));
    Usuario usuarioDb = usuarioRepository.findById(usuarioId).get();
    Optional<Estabelecimento> usuarioEstabelecimentoDb = repository.findById(usuarioDb.getEstabelecimento().getId());
    if(usuarioEstabelecimentoDb.isPresent()) {
      return ResponseEntity.badRequest().body(JsonResponse.message("Usuario ja possui estabelecimento"));
    }
    Optional<Estabelecimento> estabelecimentoDb = repository.findByCnpj(form.getCnpj());
    if(estabelecimentoDb.isPresent()) {
      return ResponseEntity.badRequest().body(JsonResponse.message("Estabelecimento já cadastrado!"));
    }
    return ResponseEntity.status(201).body(EstabelecimentoDto.converter(repository.save(form.converter(usuarioDb))));
  }
  @Operation(summary = "Atualiza dados do estabelecimento", security = { @SecurityRequirement(name = "bearer-key")})
  @ApiResponse(responseCode = "200", description = "REGISTRO ATUALIZADO COM SUCESSO")
  @ApiResponse(responseCode = "400", description = "FALHA AO ATUALIZAR REGISTRO")
  @PutMapping
  public ResponseEntity<?> update(@RequestHeader("Authorization") String token, @RequestBody @Valid EstabelecimentoForm form) {
    Long usuarioId = tokenService.getUsuarioId(token.substring(7));
    Usuario usuario = usuarioRepository.findById(usuarioId).get();
    Optional<Estabelecimento> estabelecimentoDb = repository.findById(usuario.getEstabelecimento().getId());
    if(!estabelecimentoDb.isPresent()) return ResponseEntity.badRequest().body(JsonResponse.message("Estabelecimento nao encontrado"));
    Estabelecimento estabelecimento = form.converter(estabelecimentoDb.get());
    return ResponseEntity.ok(EstabelecimentoDto.converter(repository.save(estabelecimento)));
  }
  @Operation(summary = "Deleta estabelecimentos", security = { @SecurityRequirement(name = "bearer-key")})
  @ApiResponse(responseCode = "200", description = "ESTABELECIMENTO EXCLUIDO COM SUCESSO")
  @ApiResponse(responseCode = "400", description = "FALHA AO EXCLUIR ESTABELECIMENTO")
  @DeleteMapping
  public ResponseEntity<String> destroy(@RequestHeader("Authorization") String token) {
    Long usuarioId = tokenService.getUsuarioId(token.substring(7));
    Usuario usuario = usuarioRepository.findById(usuarioId).get();
    Optional<Estabelecimento> estabelecimento = repository.findById(usuario.getEstabelecimento().getId());
    if (!estabelecimento.isPresent()) return ResponseEntity.badRequest().body(JsonResponse.message("Estabelecimento nao encontrado"));
    repository.delete(estabelecimento.get());
    return ResponseEntity.ok(JsonResponse.message("Estabelecimento excluido com sucesso"));
  }
}
