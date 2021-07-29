package com.fcamara.desafiobackend.util;


import com.fcamara.desafiobackend.model.Veiculo;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class JsonResponse {

  public static String message(String message) {
    return new JSONObject()
            .put("message", message)
            .toString();
  }
  public static String retiradoMensagem(String mensagem, Veiculo veiculo, double valor) {
    return new JSONObject()
            .put("mensagem", mensagem)
            .put("placa", veiculo.getPlaca())
            .put("saida", LocalDateTime.now())
            .put("valor", valor)
            .toString();
  }

  public static String loginMessage(String token, String tipo) {
    return new JSONObject()
            .put("token", token)
            .put("tipo", tipo)
            .toString();
  }
}
