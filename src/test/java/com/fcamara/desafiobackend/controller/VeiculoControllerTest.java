package com.fcamara.desafiobackend.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class VeiculoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void deveriaListarVeiculos() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/veiculos"))
            .andExpect(MockMvcResultMatchers.status().is(200));
  }

  @Test
  void deveriaCadastrarUmVeiculo() throws Exception {

    String body = new JSONObject()
            .put("marca", "Chevrolet")
            .put("modelo", "Spin")
            .put("cor", "Cinza")
            .put("placa", "ABC1029")
            .put("tipoVeiculo", "CARRO")
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post("/veiculos").content(body).contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().is(201))
            .andExpect(MockMvcResultMatchers.content().json("{\"marca\":\"Chevrolet\"}"));
  }
}
