package com.toth.resource;


import com.toth.model.Professor;
import com.toth.repository.ProfessorRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController()
@RequestMapping("/professores")
public class ProfessorAutenticacao {

    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping("/autenticacao")
    public ResponseEntity<?> professorLogin(@RequestBody Professor professor){
        Optional<Professor> professorProcurado = professorRepository.findByLogin(professor.getLogin());
        if(professorProcurado.isPresent())
            if(professorProcurado.get().getSenha().equals(professor.getSenha()))
                return ResponseEntity.ok().body(new JSONObject().put("status", "autenticado").toString());
            else
                return ResponseEntity.badRequest().body(new JSONObject().put("status", "Senha inválida").toString());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JSONObject().put("status", "Usuario com esse amil não cadasrado").toString());
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> setProfessor(@Valid @RequestBody Professor professor, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(ValidacoesFormat.formatarErros(bindingResult));

        if(professorRepository.existsByLogin(professor.getLogin()))
            return ResponseEntity.badRequest().body(new JSONObject().put("msg", "Login ja cadastrado").toString());
        if(professorRepository.existsByRg(professor.getRg()))
            return ResponseEntity.badRequest().body(new JSONObject().put("msg", "Rg ja cadsatrado").toString());
        else
            return ResponseEntity.ok(professorRepository.save(professor));

    }
}
