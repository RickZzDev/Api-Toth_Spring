package com.toth.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.toth.model.Atividade;
import com.toth.model.Aula;
import com.toth.model.Turma;
import com.toth.model.dto.atividade.AtividadeDTO;
import com.toth.repository.AtividadeRepository;
import com.toth.repository.AulaRepository;
import com.toth.repository.TurmaRepository;

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

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AulaRepository aulaRepository;

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
    private ResponseEntity<?> atividadeCadastro(@RequestBody AtividadeDTO atividadeDTO) {

        Long idAula = atividadeDTO.getId_aula();

        Aula aula = aulaRepository.findById(idAula).get();

        List<Long> idTurma = atividadeDTO.getId_turma();

        List<Turma> turmas = turmaRepository.findAllById(idTurma);

        atividadeDTO.setTurmas(turmas);

        atividadeDTO.setAula(aula);

        Optional<AtividadeDTO> atividadeOptional = Optional.of(atividadeDTO);
        Atividade atividade = atividadeOptional.map(Atividade::new).get();

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

    @GetMapping("/professor/{idProfessor}")
    public ResponseEntity<?> listarAtividadesProfessor(@PathVariable Long idProfessor) {
        List<Atividade> atividadesCadastradas = atividadeRepository.findAll();

        List<Atividade> atividadesDoProfessor = new ArrayList<>();

        atividadesCadastradas.forEach(atividade -> {

            for (Atividade atividade2 : atividadesCadastradas) {

                if (atividade2.getAulas().getProfessor().getId().equals(idProfessor)) {
                    atividadesDoProfessor.add(atividade2);
                    break;
                }
                if (atividadesDoProfessor.contains(atividade))
                    break;

            }

        });

        return ResponseEntity.ok().body(atividadesDoProfessor);
    }

}