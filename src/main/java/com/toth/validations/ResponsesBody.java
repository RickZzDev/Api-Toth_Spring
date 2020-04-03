package com.toth.validations;

import org.json.JSONObject;

public class ResponsesBody {

    public final static String ESCOLA_NOT_FOUND = jsonResponse(1);
    public final static String PROFESSOR_NOT_FOUND = jsonResponse(2);
    public final static String LOGIN_NOT_FOUND = jsonResponse(3);
    public final static String CNPJ_NOT_FOUND = jsonResponse(4);
    public final static String SENHA_INVALIDA = jsonResponse(5);
    public final static String LOGIN_CADASTRADO = jsonResponse(6);
    public final static String CNPJ_CADASTRADO = jsonResponse(7);
    public final static String RG_CADASTRADO = jsonResponse(8);
    public final static String CNPJ_INVALIDO = jsonResponse(9);
    public final static String CNPJ_VALIDO = jsonResponse(10);


    private static String jsonResponse(int i) {
        switch (i) {
            case 1:
                return new JSONObject().put("mensagem", "Escola não encontrada").toString();
            case 2:
                return new JSONObject().put("mensagem", "Professor não encontrado").toString();
            case 3:
                return new JSONObject().put("mensagem", "Login não encontrado").toString();
            case 4:
                return new JSONObject().put("mensagem", "CNPJ não encontrado").toString();
            case 5:
                return new JSONObject().put("mensagem", "Senha inválida").toString();
            case 6:
                return new JSONObject().put("mensagem", "Login já cadastrado").toString();
            case 7:
                return new JSONObject().put("mensagem", "CNPJ já cadastrado").toString();
            case 8:
                return new JSONObject().put("mensagem", "RG já cadastrado").toString();
            case 9:
                return new JSONObject().put("status", "CNPJ inválido!").toString();
            case 10:
                return new JSONObject().put("status", "CNPJ válido!").toString();
            default:
                throw new IllegalArgumentException();
        }
    }

}
