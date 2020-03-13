package com.toth.resource;


import com.toth.model.Professor;
import com.toth.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorResource {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("")
    public List<Professor> getProfessors(){return professorRepository.findAll(); }

    @PostMapping("")
    public void setProfessor(@RequestBody Professor professor) {professorRepository.save(professor);}

}
