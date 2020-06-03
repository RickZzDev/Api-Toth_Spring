package com.toth.resource;

import com.toth.model.Aulas;
import com.toth.repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aulas")
public class AulaResouce {

    @Autowired
    private AulaRepository aulaRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    private List<Aulas> getAulas(){return aulaRepository.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getAulaById(@PathVariable Long id){
        Optional<?>aulaProcurada = aulaRepository.findById(id);
        return aulaProcurada.isPresent() ? ResponseEntity.ok().body(aulaProcurada) : ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<?> aulaCadastro(@RequestBody @Valid Aulas aula){
        return ResponseEntity.ok().body(aulaRepository.save(aula));
    }
}
