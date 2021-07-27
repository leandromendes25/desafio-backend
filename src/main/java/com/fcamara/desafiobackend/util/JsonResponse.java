package com.fcamara.desafiobackend.util;


import org.json.JSONObject;

public class JsonResponse {

  public static String message(String message) {
    return new JSONObject()
            .put("message", message)
            .toString();
  }
}
