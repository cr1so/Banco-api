package com.fpmislata.banco.presentacion.json.impl;

import com.fasterxml.jackson.databind.ObjectMapper;import com.fpmislata.banco.presentacion.json.JsonTransformer;

public class JsonTransformerImplJackson implements JsonTransformer {

    @Override
    public String toJson(Object data) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String jsonusuario = objectMapper.writeValueAsString(data);

            return jsonusuario;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T> T fromJSON(String json, Class<T> clazz) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            return objectMapper.readValue(json, clazz);
         } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
