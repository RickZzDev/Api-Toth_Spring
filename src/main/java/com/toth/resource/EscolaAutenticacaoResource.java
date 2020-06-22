package com.toth.resource;

import com.toth.model.Escola;
import com.toth.repository.EscolaRepository;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/escolas")
@CrossOrigin
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
                return ResponseEntity.badRequest().body(com.toth.validations.ResponsesBody.SENHA_INVALIDA);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(com.toth.validations.ResponsesBody.ESCOLA_NOT_FOUND);

    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> escolaCadastro(@RequestBody @Valid Escola escola, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(com.toth.validations.ValidacoesFormat.formatarErros(bindingResult));

        if(escolaRepository.existsByLogin(escola.getLogin()))
            return ResponseEntity.badRequest().body(com.toth.validations.ResponsesBody.LOGIN_CADASTRADO);
        else if(escolaRepository.existsByCnpj(escola.getCnpj()))
            return ResponseEntity.badRequest().body(com.toth.validations.ResponsesBody.CNPJ_CADASTRADO);
        else
            return ResponseEntity.ok().body(escolaRepository.save(escola));
    }

}
