package com.toth.resource;

import com.toth.model.Escola;
import com.toth.repository.EnderecoRepository;
import com.toth.repository.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/escolas")
public class EscolaResource {

    @Autowired
    private EscolaRepository escolaRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Escola> getEscolas() {
        return escolaRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getEscolaById(@PathVariable Long id) {
        Optional<?> escolaProcurada = escolaRepository.findById(id);
        return escolaProcurada.isPresent() ? ResponseEntity.ok(escolaProcurada) : ResponseEntity.notFound().build();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Escola setEscola(@RequestBody Escola escola) {
        return escolaRepository.save(escola);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Escola updateEscola(@RequestBody Escola escola) {
        return escolaRepository.save(escola);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEscola(@PathVariable Long id) {
        escolaRepository.deleteById(id);
    }

}
