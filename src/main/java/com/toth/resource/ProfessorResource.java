package com.toth.resource;


import com.toth.model.Professor;
import com.toth.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @GetMapping("/{id}")
    public ResponseEntity<?>  getProfessor(@PathVariable Long id){
        return ResponseEntity.ok().body(professorRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        professorRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void atualizar(@RequestBody Professor professor ){
        professorRepository.save(professor);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> professorCadastro(@RequestBody @Valid Professor professor){
        return ResponseEntity.ok().body(professorRepository.save(professor));
    }

}
