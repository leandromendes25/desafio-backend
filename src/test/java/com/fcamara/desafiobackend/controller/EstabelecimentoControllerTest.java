package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.model.Endereco;
import com.fcamara.desafiobackend.model.Telefone;
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
public class EstabelecimentoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void deveriaCadastrarUmEstabelecimento() throws Exception {

    Telefone telefone = new Telefone("11989345678");

//    Endereco endereco = new Endereco("SP", "Sao Paulo", "Sapopemba", "03928130", "Rua Tal", 9);

    String endereco = new JSONObject()
            .put("estado", "SP")
            .put("cidade", "São Paulo")
            .put("bairro", "Sapopemba")
            .put("cep", "03928130")
            .put("rua", "Rua Tal")
            .put("numero", 20)
            .toString();

    String form = new JSONObject()
            .put("nome", "Estacionamento TakamassaNoMuro")
            .put("cnpj", "03.775.758/0001-91")
            .put("endereco", endereco)
            .put("telefone", telefone)
            .put("vagasMotos", 20)
            .put("vagasCarros", 25)
            .toString();

    mockMvc.perform(MockMvcRequestBuilders
      .post("/estabelecimentos")
      .content(form)
      .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().is(201))
            .andExpect(MockMvcResultMatchers.content().json("{\"nome\":\"Estacionamento TakamassaNoMuro\"}"));
  }
}
