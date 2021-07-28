package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.model.Endereco;
import com.fcamara.desafiobackend.model.Estabelecimento;
import com.fcamara.desafiobackend.repository.EstabelecimentoRepository;
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
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EstabelecimentoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private EstabelecimentoRepository repository;

  @Test
  @Order(2)
  void deveriaListarEstabelecimentos() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/estabelecimentos"))
            .andExpect(MockMvcResultMatchers.status().is(200));
  }

  @Test
  @Order(1)
  void deveriaCadastrarUmEstabelecimento() throws Exception {

    JSONObject endereco = new JSONObject()
            .put("rua", "Rua Teste")
            .put("cep", "08820390")
            .put("numero", 10)
            .put("bairro", "César de Souza")
            .put("cidade", "Mogi das Cruzes")
            .put("estado", "São Paulo");


    String telefone = new JSONObject()
            .put("numero", "11989365271")
            .toString();

    String body = new JSONObject()
            .put("nome", "Estacionamento teste")
            .put("cnpj", "03.775.758/0001-90")
            .put("endereco", endereco)
            .put("telefone", telefone)
            .put("vagasMotos", 10)
            .put("vagasCarros", 10)
            .put("valorHora", 9)
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post("/estabelecimentos").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(MockMvcResultMatchers.status().is(201))
            .andExpect(MockMvcResultMatchers.content().json("{\"nome\":\"Estacionamento teste\"}"));
  }

  @Test
  @Order(3)
  void deveriaEditarUmEstabelecimento() throws Exception {

    JSONObject endereco = new JSONObject()
            .put("rua", "Rua Teste")
            .put("cep", "08820390")
            .put("numero", 10)
            .put("bairro", "César de Souza")
            .put("cidade", "Mogi das Cruzes")
            .put("estado", "São Paulo");


    String telefone = new JSONObject()
            .put("numero", "11989365271")
            .toString();

    String body = new JSONObject()
            .put("nome", "Estacionamento Atualizado")
            .put("cnpj", "03.775.758/0001-90")
            .put("endereco", endereco)
            .put("telefone", telefone)
            .put("vagasMotos", 10)
            .put("vagasCarros", 10)
            .put("valorHora", 9)
            .toString();

    Estabelecimento estabelecimento = repository.findByCnpj("03.775.758/0001-90").get();
    mockMvc.perform(MockMvcRequestBuilders.put("/estabelecimentos/" + estabelecimento.getId()).contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.content().json("{\"nome\":\"Estacionamento Atualizado\"}"));

  }

  @Test
  @Order(4)
  void deveriaExcluirUmEstabelecimento() throws Exception {
    Estabelecimento estabelecimento = repository.findByCnpj("03.775.758/0001-90").get();
    mockMvc.perform(MockMvcRequestBuilders.delete("/estabelecimentos/" +estabelecimento.getId()))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.content().json("{\"message\":\"Estabelecimento excluido com sucesso\"}"));
  }
}