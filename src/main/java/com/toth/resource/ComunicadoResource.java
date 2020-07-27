package com.toth.resource;

import java.util.Optional;

import javax.validation.Valid;

import com.toth.model.Aula;
import com.toth.model.ComunicadoProfessor;
import com.toth.model.dto.comunicado.ComunicadoRequest;
import com.toth.repository.AulaRepository;
import com.toth.repository.ComunicadoRepository;

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
@RequestMapping("/comunicados")
public class ComunicadoResource {

    @Autowired
    private ComunicadoRepository comunicadoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getComunicados() {
        return ResponseEntity.ok().body(comunicadoRepository.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getComunicadoById(@PathVariable Long id) {
        Optional<?> comunicadoProcurado = comunicadoRepository.findById(id);
        return comunicadoProcurado.isPresent() ? ResponseEntity.ok().body(comunicadoProcurado)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<?> comunicadoCadastro(@RequestBody @Valid ComunicadoRequest comunicado) {

        Aula aula = aulaRepository.findById(comunicado.getIdAula()).get();

        ComunicadoProfessor comunicadoObj = new ComunicadoProfessor();

        comunicadoObj.setAula(aula);
        comunicadoObj.setDescription(comunicado.getDescription());
        comunicadoObj.setGeral(comunicado.isGeral());
        comunicadoObj.setPublico_alvo(comunicado.getPublico_alvo());
        comunicadoObj.setTitle(comunicado.getTitle());
        comunicadoObj.setTurmas(comunicado.getTurmas());

        return ResponseEntity.ok().body(comunicadoRepository.save(comunicadoObj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComunicado(@PathVariable Long id) {
        if (comunicadoRepository.existsById(id)) {
            comunicadoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseEntity.notFound());
    }

}