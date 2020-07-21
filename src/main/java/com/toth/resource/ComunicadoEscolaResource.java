package com.toth.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.toth.model.ComunicadoEscola;
import com.toth.repository.ComunicadosEscolaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comunicados/escola")
public class ComunicadoEscolaResource {

    @Autowired
    private ComunicadosEscolaRepository comunicadoEscola;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    private List<ComunicadoEscola> getComu() {
        return comunicadoEscola.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getComuEscolaById(@PathVariable Long id) {
        Optional<?> comunicadoProcurado = comunicadoEscola.findById(id);
        return comunicadoProcurado.isPresent() ? ResponseEntity.ok().body(comunicadoProcurado)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<?> comuEscolaCadastro(@RequestBody @Valid ComunicadoEscola comunicado) {
        return ResponseEntity.ok().body(comunicadoEscola.save(comunicado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComuEscola(@PathVariable Long id) {
        if (comunicadoEscola.existsById(id)) {
            comunicadoEscola.deleteById(id);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseEntity.notFound());
    }

}