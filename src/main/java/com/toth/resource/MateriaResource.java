package com.toth.resource;

import com.toth.model.Materia;
import com.toth.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materias")
public class MateriaResource {

    @Autowired
    private MateriaRepository materiaRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    private List<Materia> getMaterias() {
        return materiaRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getMateriaById(@PathVariable Long id) {
        Optional<?> materiaProcurada = materiaRepository.findById(id);
        return materiaProcurada.isPresent() ? ResponseEntity.ok().body(materiaProcurada)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<?> materiaCadastro(@RequestBody Materia materia) {
        return ResponseEntity.ok().body(materiaRepository.save(materia));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void excluirMatéria(@PathVariable Long id) {
        materiaRepository.deleteById(id);
    }

}
