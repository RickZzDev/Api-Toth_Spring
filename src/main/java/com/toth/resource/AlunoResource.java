package com.toth.resource;

import com.toth.model.Acesso;
import com.toth.model.Aluno;
import com.toth.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    private List<Aluno> getAlunos(){return alunoRepository.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlunoById(@PathVariable Long id){
        Optional <?> alunoProcurado = alunoRepository.findById(id);
        return  alunoProcurado.isPresent() ? ResponseEntity.ok(alunoProcurado) : ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> alunoCadastro(@RequestBody @Valid Aluno aluno){
        return ResponseEntity.ok().body(alunoRepository.save(aluno));
    }
}
