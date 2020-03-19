package com.toth.resource;


import com.toth.model.Professor;
import com.toth.repository.ProfessorRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
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
            return ResponseEntity.notFound().build();
    }
}
