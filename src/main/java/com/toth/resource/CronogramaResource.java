package com.toth.resource;

import com.toth.model.Aula;
import com.toth.model.Cronograma;
import com.toth.model.Cronograma;
import com.toth.model.DiaLetivo;
import com.toth.model.dto.cronograma.CronogramaRequest;
import com.toth.repository.AulaRepository;
import com.toth.repository.CronogramaRepository;
import com.toth.repository.CronogramaRepository;
import com.toth.repository.DiaLetivoRepository;
import com.toth.validations.ResponsesBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cronograma")
public class CronogramaResource {

    @Autowired
    CronogramaRepository cronogramaRepository;

    @Autowired
    DiaLetivoRepository diaLetivoRepository;

    private static final String URI_ENTIDADE_CRIADA = "/cronograma/";

    @GetMapping("")
    public ResponseEntity<?> listarCronograma() {
        return ResponseEntity.ok().body(cronogramaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarCronograma(@PathVariable Long id) {
        Optional<?> cronogramaBuscado = cronogramaRepository.findById(id);
        return cronogramaBuscado.isPresent() ?
                ResponseEntity.ok().body(cronogramaRepository.findById(id))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponsesBody.RECURSO_NOT_FOUND);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCronograma(@RequestBody CronogramaRequest cronogramaRequest) {
        List<DiaLetivo> diasLetivos = diaLetivoRepository.findAllById(cronogramaRequest.getIdsDiasLetivos());

        Cronograma cronograma = new Cronograma();
        cronograma.setDiasLetivos(diasLetivos);

        diasLetivos.forEach(diaLetivo -> System.out.println(diaLetivo));
        System.out.println(cronograma);
        Cronograma cronogramaCadastrado = cronogramaRepository.save(cronograma);

        return ResponseEntity
                .created(URI.create(URI_ENTIDADE_CRIADA + cronogramaCadastrado.getId().toString()))
                .body(cronogramaCadastrado);
    }

    @DeleteMapping("/{id}")
    public void deletarCronograma(@PathVariable Long id) {
        cronogramaRepository.deleteById(id);
    }

}
