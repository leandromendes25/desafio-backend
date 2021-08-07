package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.controller.form.UsuarioForm;
import com.fcamara.desafiobackend.model.Usuario;
import com.fcamara.desafiobackend.repository.UsuarioRepository;
import com.fcamara.desafiobackend.util.JsonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Tag(name="Controle de usuarios")
@RequestMapping("/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioRepository repository;
  @Operation(summary = "Registra um novo usuário")
  @ApiResponse(responseCode = "200", description = "USUARIO REGISTRADO COM SUCESSO")
  @ApiResponse(responseCode = "400", description = "FALHA AO REGISTRAR NOVO USUÁRIO")
  @PostMapping
  public ResponseEntity<String> store(@RequestBody UsuarioForm form) {
    Optional<Usuario> usuarioDb = repository.findByEmail(form.getEmail());
    if(usuarioDb.isPresent()) return ResponseEntity.badRequest().body(JsonResponse.message("E-mail ja utilizado"));
    Usuario usuario = form.converter();
    repository.save(usuario);
    return ResponseEntity.status(201).body(JsonResponse.message("Usuario cadastrado com o email " + form.getEmail()));
  }
}
