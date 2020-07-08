package com.toth.resource;

import com.toth.model.Acesso;
import com.toth.model.Escola;
import com.toth.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acessos")
public class AcessoResource {

    @Autowired
    private AcessoRepository acessoRepository;

    @GetMapping("")
    public List<Acesso> getAcessos() {
        return acessoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAcessoById(@PathVariable Long id) {
        Optional<?> acessoProcurado = acessoRepository.findById(id);
        return acessoProcurado.isPresent() ? ResponseEntity.ok(acessoProcurado) : ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> acessoCadastro(@RequestBody @Valid Acesso acesso) {
        return ResponseEntity.ok().body(acessoRepository.save(acesso));
    }
}
