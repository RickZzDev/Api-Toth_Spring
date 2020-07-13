package com.toth.resource;

import com.toth.model.Cronograma;
import com.toth.model.Turma;
import com.toth.model.dto.turma.TurmaRequest;
import com.toth.repository.CronogramaRepository;
import com.toth.repository.TurmaRepository;
import com.toth.validations.ResponsesBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turmas")
public class TurmaResource {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    private List<Turma> getTurmas(){return turmaRepository.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getTurmaById(@PathVariable Long id){

        Optional<?> turmaProcurada = turmaRepository.findById(id);
        return  turmaProcurada.isPresent() ? ResponseEntity.ok().body(turmaProcurada) : ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<?> turmaCadastro(@RequestBody @Valid TurmaRequest turmaRequest){

        Cronograma cronograma = cronogramaRepository.findById(turmaRequest.getIdCronograma()).get();
        turmaRequest.setCronograma(cronograma);

        Optional<TurmaRequest> turmaOptional = Optional.of(turmaRequest);
        Turma turma = turmaOptional.map(Turma::new).get();

        return ResponseEntity.ok().body(turmaRepository.save(turma));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTurma(@PathVariable Long id) {
        if(turmaRepository.existsById(id)){
            turmaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseEntity.notFound());
    }
}
