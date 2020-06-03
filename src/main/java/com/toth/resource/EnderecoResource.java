package com.toth.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.toth.model.Endereco;
import com.toth.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("")
    public List<Endereco> getEnderecos() {
        return enderecoRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getEnderecoBydId(@PathVariable Long id){
        Optional<?> enderecoProcurado = enderecoRepository.findById(id);
        return enderecoProcurado.isPresent() ? ResponseEntity.ok().body(enderecoProcurado) : ResponseEntity.notFound().build();
    }

}
