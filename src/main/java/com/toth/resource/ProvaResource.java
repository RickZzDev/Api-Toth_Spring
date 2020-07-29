package com.toth.resource;

import java.util.Optional;

import javax.validation.Valid;

import com.toth.model.Atividade;
import com.toth.model.Aula;
import com.toth.model.Prova;
import com.toth.model.dto.prova.ProvaRequest;
import com.toth.repository.AtividadeRepository;
import com.toth.repository.AulaRepository;
import com.toth.repository.ProvaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/provas")
public class ProvaResource {
    @Autowired
    private ProvaRepository provaRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getComunicados() {
        return ResponseEntity.ok().body(provaRepository.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getComunicadoById(@PathVariable Long id) {
        Optional<?> comunicadoProcurado = provaRepository.findById(id);
        return comunicadoProcurado.isPresent() ? ResponseEntity.ok().body(comunicadoProcurado)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<?> comunicadoCadastro(@RequestBody ProvaRequest provaRequest) {

        Long idAula = provaRequest.getId_aula();

        Aula aulaProcurada = aulaRepository.findById(idAula).get();

        Prova prova = new Prova();
        prova.setAula(aulaProcurada);
        prova.setAtividadesParaEstudar(provaRequest.getAtividadesParaEstudar());
        prova.setConteudo(provaRequest.getConteudo());
        prova.setDiaProva(provaRequest.getDiaProva());
        prova.setPesoProva(provaRequest.getPesoProva());

        return ResponseEntity.ok().body(provaRepository.save(prova));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComunicado(@PathVariable Long id) {
        if (provaRepository.existsById(id)) {
            provaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseEntity.notFound());
    }
}