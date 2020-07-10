package com.toth.resource;


import com.toth.model.Professor;
import com.toth.model.dto.professores.ProfessorResponse;
import com.toth.repository.ProfessorRepository;
import com.toth.validations.ResponsesBody;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professores")
public class ProfessorResource {

    @Autowired
    private ProfessorRepository professorRepository;


    @GetMapping("")
    public List<Professor> getProfessors(){return professorRepository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getProfessor(@PathVariable Long id){
//       Optional<Professor> professorProcurado = professorRepository.findById(id);
//
//       ProfessorResponse professorResponse = professorProcurado.map(ProfessorResponse::new).get();
//       System.out.println(professorResponse.getId());
//       if(professorProcurado.isPresent())
//            return ResponseEntity.ok().body(professorResponse);
//       else
//           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponsesBody.PROFESSOR_NOT_FOUND);
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
