package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.config.security.TokenService;
import com.fcamara.desafiobackend.controller.form.UsuarioForm;
import com.fcamara.desafiobackend.util.JsonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name="Controle de Autenticação")
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private TokenService tokenService;
  @Operation(summary = "Efetua login da aplicação e retorna token")
  @ApiResponse(responseCode = "200", description = "TOKEN GERADO COM SUCESSO")
  @ApiResponse(responseCode = "400", description = "FALHA NA GERAÇÃO DO TOKEN")
  @PostMapping
  public ResponseEntity<?> autenticarUsuario(@RequestBody @Valid UsuarioForm form) {
    UsernamePasswordAuthenticationToken dadosLogin = form.loginConvert();

    try {
      Authentication authentication = authManager.authenticate(dadosLogin);
      String token = tokenService.gerarToken(authentication);
      return ResponseEntity.ok(JsonResponse.loginMessage(token, "Bearer"));
    } catch (AuthenticationException e) {
      return ResponseEntity.badRequest().body(JsonResponse.message("Email ou senha incorretos"));
    }
  }
}
