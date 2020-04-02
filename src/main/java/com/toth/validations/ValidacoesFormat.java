package com.toth.validations;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ValidacoesFormat {

    // Este método recebe um bindingResult contendo os erros capturados na validação de um campo.
    // Formata os erros em um JSON que fornece o campo onde a validação falhou, o valor inserido e a mensagem de erro.
    public static String formatarErros(BindingResult bindingResult) {
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();
        List<FieldError> fieldErrorsList = bindingResult.getFieldErrors(); // Lista com os campos em que a validação capturou erros.

        for (FieldError erro: fieldErrorsList) {
            jsonObject = new JSONObject().put("campo", erro.getField());
            jsonObject.put("valor", erro.getRejectedValue());
            jsonObject.put("mensagemErro", erro.getDefaultMessage());
            jsonArray.put(jsonObject);
        }

        JSONObject json = new JSONObject().put("Erros", jsonArray);
        return json.toString();
    }
}
