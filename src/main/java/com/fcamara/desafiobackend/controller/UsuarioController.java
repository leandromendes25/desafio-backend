package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.controller.form.UsuarioForm;
import com.fcamara.desafiobackend.model.Usuario;
import com.fcamara.desafiobackend.repository.UsuarioRepository;
import com.fcamara.desafiobackend.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioRepository repository;

  @PostMapping
  public ResponseEntity<String> store(@RequestBody UsuarioForm form) {
    Optional<Usuario> usuarioDb = repository.findByEmail(form.getEmail());
    if(usuarioDb.isPresent()) return ResponseEntity.badRequest().body(JsonResponse.message("E-mail ja utilizado"));
    Usuario usuario = form.converter();
    repository.save(usuario);
    return ResponseEntity.status(201).body(JsonResponse.message("Usuario cadastrado com o email " + form.getEmail()));
  }
}
