package com.fpmislata.banco.presentacion.json;

public interface JsonTransformer{

    String toJson(Object data);

    <T> T fromJSON(String json, Class<T> clazz);
            
}
