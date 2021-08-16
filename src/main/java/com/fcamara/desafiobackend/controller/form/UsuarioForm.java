package com.fcamara.desafiobackend.controller.form;

import com.fcamara.desafiobackend.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioForm {

  @NotNull
  private String email;
  @NotNull
  private String senha;

  public Usuario converter() {
    String senha = new BCryptPasswordEncoder().encode(this.senha);
    return new Usuario(email, senha);
  }

  public UsernamePasswordAuthenticationToken loginConvert() {
    return new UsernamePasswordAuthenticationToken(email, senha);
  }
}
