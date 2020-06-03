package com.toth.resource;

import com.toth.model.Contato;
import com.toth.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatoResource {

    @Autowired
    private ContatoRepository contatoRepository;


    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    private List<Contato> getContatos(){return contatoRepository.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getContatoById(@PathVariable Long id){
        Optional<?> contatoProcurado = contatoRepository.findById(id);
        return contatoProcurado.isPresent() ? ResponseEntity.ok().body(contatoProcurado) : ResponseEntity.notFound().build();

    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity <?> contatoCadatro(@RequestBody @Valid Contato contato){
        return ResponseEntity.ok().body(contatoRepository.save(contato));
    }
}
