package com.toth.resource;

import com.toth.model.Escola;
import com.toth.repository.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> setEscola(@RequestBody @Valid Escola escola, BindingResult bindResult){
        if(bindResult.hasErrors())
            return ResponseEntity.badRequest().body(ValidacoesFormat.formatarErros(bindResult));

        if(escolaRepository.existsByLogin(escola.getLogin()))
            return ResponseEntity.badRequest().body("Login ja cadastrado");
        else if(escolaRepository.existsByCnpj(escola.getCnpj()))
            return ResponseEntity.badRequest().body("cnpj ja cadastrado");
        else
            return ResponseEntity.ok(escolaRepository.save(escola));
        
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateEscola(@Valid @RequestBody Escola escola, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(ValidacoesFormat.formatarErros(bindingResult));

        return ResponseEntity.ok(escolaRepository.save(escola));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEscola(@PathVariable Long id) {
        if(escolaRepository.existsById(id)){
            escolaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }

}
