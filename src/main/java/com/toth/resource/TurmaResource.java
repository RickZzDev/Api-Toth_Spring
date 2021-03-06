package com.toth.resource;

import com.toth.model.Ano;
import com.toth.model.Aula;
import com.toth.model.Cronograma;
import com.toth.model.DiaLetivo;
import com.toth.model.Professor;
import com.toth.model.Turma;
import com.toth.model.dto.turma.TurmaAnoIden;
import com.toth.model.dto.turma.TurmaRequest;
import com.toth.repository.AnoRepository;
import com.toth.repository.AulaRepository;
import com.toth.repository.CronogramaRepository;
import com.toth.repository.ProfessorRepository;
import com.toth.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turmas")
public class TurmaResource {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private AnoRepository anoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    private List<Turma> getTurmas() {
        return turmaRepository.findAll();
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateTurma(@RequestBody Turma turma) {

        return ResponseEntity.ok().body(turmaRepository.save(turma));

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getTurmaById(@PathVariable Long id) {

        Optional<?> turmaProcurada = turmaRepository.findById(id);
        return turmaProcurada.isPresent() ? ResponseEntity.ok().body(turmaProcurada)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/lazy")
    @ResponseStatus(HttpStatus.OK)
    private List<TurmaAnoIden> getTurmaByAnoIden() {
        TurmaAnoIden teste = new TurmaAnoIden();
        List<Turma> turmas = turmaRepository.findAll();

        List<TurmaAnoIden> turmasAtualizada = teste.toTurma(turmas);

        return turmasAtualizada;

    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<?> turmaCadastro(@RequestBody @Valid TurmaRequest turmaRequest) {

        // Cronograma cronograma =
        // cronogramaRepository.findById(turmaRequest.getIdCronograma()).get();
        // turmaRequest.setCronograma(cronograma);

        Ano ano = anoRepository.findById(turmaRequest.getIdAno()).get();
        turmaRequest.setAno(ano);

        Optional<TurmaRequest> turmaOptional = Optional.of(turmaRequest);
        Turma turma = turmaOptional.map(Turma::new).get();

        return ResponseEntity.ok().body(turmaRepository.save(turma));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTurma(@PathVariable Long id) {
        if (turmaRepository.existsById(id)) {
            turmaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseEntity.notFound());
    }

    @GetMapping("/professores/{idProfessor}")
    public ResponseEntity<?> listarTurmasDoProfessor(@PathVariable Long idProfessor) {
        Professor professor = professorRepository.findById(idProfessor).get();

        Aula aulaMinistradasPeloProfessor = aulaRepository.findByProfessor(professor);

        List<Turma> turmasDoProfessor = turmaRepository.findByAulas(aulaMinistradasPeloProfessor);

        // List<Turma> turmasDoProfessor =
        // turmaRepository.findByAulas(aulasMinistradasPeloProfessor);

        return ResponseEntity.ok(turmasDoProfessor);
    }

}
