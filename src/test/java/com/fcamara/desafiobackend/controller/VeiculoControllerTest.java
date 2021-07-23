package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.model.Veiculo;
import com.fcamara.desafiobackend.repository.VeiculoRepository;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
class VeiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    VeiculoRepository repository;

    @Test
    void deveRetornarListaVeiculos() throws Exception {
        URI uri = new URI("/veiculos");
        mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void deveCriarVeiculo() throws Exception {
        URI uri = new URI("/veiculos");
        String json = new JSONObject()
                .put("marca", "Ferrari")
                .put("modelo", "Ferrari 488")
                .put("cor", "vermelho")
                .put("placa", "OSD1234")
                .put("tipo", "CARRO")
                .toString();

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().is(201));
    }

}