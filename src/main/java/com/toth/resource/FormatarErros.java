package com.toth.resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class FormatarErros {

    public static String formatarErros(List<FieldError> fieldErrors) {
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();

        for (FieldError erro: fieldErrors) {
            jsonObject = new JSONObject();
            jsonObject.put("mensagemErro", erro.getDefaultMessage());
            jsonObject.put("campo", erro.getField());
            jsonObject.put("valor", erro.getRejectedValue());
            jsonArray.put(jsonObject);
        }

        JSONObject json = new JSONObject();
        return json.put("Erros", jsonArray).toString();
    }
}
