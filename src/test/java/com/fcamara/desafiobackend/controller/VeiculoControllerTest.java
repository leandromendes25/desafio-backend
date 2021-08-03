package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.config.security.TokenService;
import com.fcamara.desafiobackend.model.Veiculo;
import com.fcamara.desafiobackend.repository.VeiculoRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VeiculoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private VeiculoRepository repository;

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private TokenService tokenService;

  private String token;

  @BeforeAll
  void login() {
    UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken("teste@usuarioteste.com", "1234");
    Authentication authentication = authManager.authenticate(dadosLogin);
    token = "Bearer " + tokenService.gerarToken(authentication);
  }

  @Test
  @Order(2)
  void deveriaListarVeiculos() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/veiculos").header("Authorization", token))
            .andExpect(MockMvcResultMatchers.status().is(200));
  }

  @Test
  @Order(1)
  void deveriaCadastrarUmVeiculo() throws Exception {

    String body = new JSONObject()
            .put("marca", "Chevrolet")
            .put("modelo", "Spin")
            .put("cor", "Cinza")
            .put("placa", "ABC1029")
            .put("tipoVeiculo", "CARRO")
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post("/veiculos").content(body)
            .contentType(MediaType.APPLICATION_JSON).header("Authorization", token))
            .andExpect(MockMvcResultMatchers.status().is(201))
            .andExpect(MockMvcResultMatchers.content().json("{\"marca\":\"Chevrolet\"}"));
  }

  @Test
  @Order(3)
  void deveriaRetornarBadRequestAoTentarCadastrarOMesmoVeiculo() throws Exception {

    String body = new JSONObject()
            .put("marca", "Chevrolet")
            .put("modelo", "Spin")
            .put("cor", "Cinza")
            .put("placa", "ABC1029")
            .put("tipoVeiculo", "CARRO")
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post("/veiculos").content(body)
            .contentType(MediaType.APPLICATION_JSON).header("Authorization", token))
            .andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  @Order(4)
  void deveriaEditarOVeiculo() throws Exception {

    String body = new JSONObject()
            .put("marca", "Chevrolet")
            .put("modelo", "Prisma")
            .put("cor", "Cinza")
            .put("placa", "ABC1029")
            .put("tipoVeiculo", "CARRO")
            .toString();

    Veiculo veiculo = repository.findByPlaca("ABC1029").get();

    mockMvc.perform(MockMvcRequestBuilders.put("/veiculos/" + veiculo.getId()).content(body)
            .contentType(MediaType.APPLICATION_JSON).header("Authorization", token))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.content().json("{\"modelo\":\"Prisma\"}"));
  }

  @Test
  @Order(5)
  void deveriaExcluirOVeiculo() throws Exception {

    Veiculo veiculo = repository.findByPlaca("ABC1029").get();

    mockMvc.perform(MockMvcRequestBuilders.delete("/veiculos/" + veiculo.getId()).header("Authorization", token))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.content().json("{\"mensagem\":\"Veiculo excluido com sucesso\"}"));
  }
}
