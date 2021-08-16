package com.fcamara.desafiobackend.config.security;

import com.fcamara.desafiobackend.model.Estabelecimento;
import com.fcamara.desafiobackend.model.Usuario;
import com.fcamara.desafiobackend.repository.EstabelecimentoRepository;
import com.fcamara.desafiobackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

  private TokenService tokenService;
  private EstabelecimentoRepository estabelecimentoRepository;
  private UsuarioRepository usuarioRepository;

  public AutenticacaoTokenFilter(TokenService tokenService, EstabelecimentoRepository estabelecimentoRepository, UsuarioRepository usuarioRepository) {
    this.tokenService = tokenService;
    this.estabelecimentoRepository = estabelecimentoRepository;
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String token = recuperarToken(request);
    boolean valido = tokenService.isTokenValid(token);
    if(valido) {
      autenticarCliente(token);
    }
    filterChain.doFilter(request, response);
  }

  private String recuperarToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
      return null;
    }

    return token.substring(7);
  }

  private void autenticarCliente(String token) {
    Long usuarioId = tokenService.getUsuarioId(token);
    Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
    if(usuario.isPresent()) {
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario.get(), null, usuario.get().getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

  }
}
