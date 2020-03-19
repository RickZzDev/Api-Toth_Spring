package com.toth.resource;

import com.toth.model.Escola;
import com.toth.repository.EscolaRepository;
import org.hibernate.loader.custom.Return;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("/escolas")
public class EscolaAutenticacaoResource {

    @Autowired
    private EscolaRepository escolaRepository;

    @PostMapping("/autenticacao")
    public ResponseEntity<?> escolaLogin(@RequestBody Escola escola) {
        Optional<Escola> escolaProcurada = escolaRepository.findByLogin(escola.getLogin());

        if (escolaProcurada.isPresent())
            if(escolaProcurada.get().getSenha().equals(escola.getSenha()))
                return ResponseEntity.ok().body(new JSONObject().put("status", "autenticado").toString());
            else
                return ResponseEntity.badRequest().body(new JSONObject().put("status", "Senha inválida").toString());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JSONObject().put("status", "Login não cadastrado").toString());

    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> escolaRegistro(@RequestBody @Valid Escola escola) {
        if(escolaRepository.existsByLogin(escola.getLogin()))
            return ResponseEntity.badRequest().body(new JSONObject().put("status", "Login já cadastrado").toString());
        else if(escolaRepository.existsByCnpj(escola.getCnpj()))
            return ResponseEntity.badRequest().body(new JSONObject().put("status", "CNPJ já cadastrado").toString());
        else
            return ResponseEntity.ok(new JSONObject().put("status", "Escola cadastrada com sucesso").toString());
    }

}
