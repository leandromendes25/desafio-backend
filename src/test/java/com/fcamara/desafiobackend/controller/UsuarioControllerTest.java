package com.fcamara.desafiobackend.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(1)
  void deveriaCadastrarUsuario() throws Exception {

    String body = new JSONObject()
            .put("email", "teste@test.com")
            .put("senha", "1234")
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post("/usuarios").content(body).contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().is(201))
            .andExpect(MockMvcResultMatchers.content().json("{\"mensagem\":\"Usuario cadastrado com o email teste@test.com\"}"));
  }

  @Test
  @Order(2)
  void deveriaRetornarBadRequestAoTentarCadastrarComEmailExistente() throws Exception {
    String body = new JSONObject()
            .put("email", "teste@test.com")
            .put("senha", "1234")
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post("/usuarios").content(body).contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().is(400))
            .andExpect(MockMvcResultMatchers.content().json("{\"mensagem\":\"E-mail ja utilizado\"}"));
  }

}