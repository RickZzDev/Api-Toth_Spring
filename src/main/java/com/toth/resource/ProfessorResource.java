package com.toth.resource;


import com.toth.model.Professor;
import com.toth.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorResource {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("")
    public List<Professor> getProfessors(){return professorRepository.findAll(); }

    @PostMapping("")
    public ResponseEntity<?> setProfessor(@Valid @RequestBody Professor professor, BindingResult bindResult) {
        if(bindResult.hasErrors()){
            return ResponseEntity.badRequest().body(ValidacoesFormat.formatarErros(bindResult));
        }
        else {
            return  ResponseEntity.ok(professorRepository.save(professor));
        }
//        professorRepository.save(professor);
    }

}
