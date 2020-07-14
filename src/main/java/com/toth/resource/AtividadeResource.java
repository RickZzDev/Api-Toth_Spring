package com.toth.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.toth.model.Atividade;
import com.toth.repository.AtividadeRepository;

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
@RequestMapping("/atividades")
public class AtividadeResource {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    private List<Atividade> getAtividades() {
        return atividadeRepository.findAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getAtividadeById(@PathVariable Long id) {
        Optional<?> atividadeProcurada = atividadeRepository.findById(id);
        return atividadeProcurada.isPresent() ? ResponseEntity.ok().body(atividadeProcurada)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<?> atividadeCadastro(@RequestBody @Valid Atividade atividade) {
        return ResponseEntity.ok().body(atividadeRepository.save(atividade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAno(@PathVariable Long id) {
        if (atividadeRepository.existsById(id)) {
            atividadeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseEntity.notFound());
    }

}