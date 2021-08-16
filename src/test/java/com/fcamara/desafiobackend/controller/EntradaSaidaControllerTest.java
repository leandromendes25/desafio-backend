package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.config.security.TokenService;
import com.fcamara.desafiobackend.model.Estabelecimento;
import com.fcamara.desafiobackend.repository.EstabelecimentoRepository;
import com.fcamara.desafiobackend.repository.VeiculoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EntradaSaidaControllerTest {

    private String token;

    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EntradaSaidaController service;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @BeforeAll
    void login() {
        UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken("teste@usuarioteste.com", "1234");
        Authentication authentication = authManager.authenticate(dadosLogin);
        token = "Bearer " + tokenService.gerarToken(authentication);
    }

    @Test
    @Order(1)
    void deveriaEstacionarVeiculo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/controle/entrada/1/1").header("Authorization",token))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json("{\"mensagem\":\"Veiculo estacionado com sucesso\"}"));    }

    @Test
    @Order(2)
    void deveriaRetornarBadRequestaAoTentarEstacionarJaEstacionado() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/controle/entrada/1/1").header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().json("{\"mensagem\":\"Veiculo já está estacionado\"}"));
    }

    @Test
    @Order(3)
    void deveriaRetirarVeiculo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/controle/saida/1/1").header("Authorization",token))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json("{\"mensagem\":\"Veiculo retirado com sucesso\"}"));
    }

    @Test
    @Order(4)
    void deveriaRetornarBadRequestAoTentarRetirarVeiculoJaRetirado() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/controle/saida/1/1").header("Authorization",token))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().json("{\"mensagem\":\"Veiculo já retirado\"}"));
    }
    @Test
    @Order(5)
    void deveriaRetornarVagasEsgotadas() throws Exception {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(Long.parseLong("1")).get();
        estabelecimento.setVagasOcupadasCarros(estabelecimento.getVagasCarros());
        estabelecimentoRepository.save(estabelecimento);
        mockMvc.perform(MockMvcRequestBuilders.put("/controle/entrada/1/1").header("Authorization",token))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().json("{\"mensagem\":\"Erro ao dar entrada\"}"));
    }
}