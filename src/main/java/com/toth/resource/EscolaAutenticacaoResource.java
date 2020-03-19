package com.toth.resource;

import com.toth.model.Escola;
import com.toth.repository.EscolaRepository;
import org.hibernate.loader.custom.Return;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return ResponseEntity.notFound().build();

    }

}
