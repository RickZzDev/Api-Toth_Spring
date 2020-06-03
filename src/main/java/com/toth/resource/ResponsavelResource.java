package com.toth.resource;

import com.toth.model.Responsavel;
import com.toth.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reponsaveis")
public class ResponsavelResource {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    private List<Responsavel> getResponsavel(){return responsavelRepository.findAll();}


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getReponsavelById(@PathVariable Long id){
        Optional<?> responsavelProcurado = responsavelRepository.findById(id);
        return responsavelProcurado.isPresent() ? ResponseEntity.ok().body(responsavelProcurado) : ResponseEntity.notFound().build();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<?> responsavelCadastro(@RequestBody @Valid Responsavel responsavel){
        return ResponseEntity.ok().body(responsavelRepository.save(responsavel));
    }
}
