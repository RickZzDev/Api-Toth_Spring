package com.toth.resource;


import com.toth.model.Ano;
import com.toth.repository.AnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/anos")
public class AnoResource {

    @Autowired
    private AnoRepository anoRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    private List<Ano> getAnos(){return anoRepository.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getAnoById(@PathVariable Long id){
        Optional<?> anoProcurado = anoRepository.findById(id);
        return anoProcurado.isPresent() ? ResponseEntity.ok().body(anoProcurado) : ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<?> anoCadastro(@RequestBody @Valid Ano ano){
        return ResponseEntity.ok().body(anoRepository.save(ano));
    }

}
