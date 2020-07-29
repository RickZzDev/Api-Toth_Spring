package com.toth.resource;

import com.toth.model.Aula;
import com.toth.model.DiaLetivo;
import com.toth.model.dto.diasletivos.DiaLetivoRequest;
import com.toth.repository.AulaRepository;
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
@RequestMapping("dias-letivos")
public class DiaLetivoResource {

    @Autowired
    DiaLetivoRepository diaLetivoRepository;

    @Autowired
    AulaRepository aulaRepository;

    private static final String URI_ENTIDADE_CRIADA = "/dias-letivos/";

    @GetMapping("")
    public ResponseEntity<?> listarDiasLetivos() {
        return ResponseEntity.ok().body(diaLetivoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarDiaLetivo(@PathVariable Long id) {
        Optional<?> diaLetivoBuscado = diaLetivoRepository.findById(id);
        return diaLetivoBuscado.isPresent() ? ResponseEntity.ok().body(diaLetivoRepository.findById(id))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponsesBody.DIA_LETIVO_NF);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarDiaLetivo(@RequestBody DiaLetivoRequest diaLetivoRequest) {

        // Buscando todas as aulas cujos id's correspondem aos id's recebidos na
        // requisição
        List<Aula> aulasCadastradas = aulaRepository.findAllById(diaLetivoRequest.getIdsAulas());

        // Instanciando um DiaLetivo e passando para ele as aulas cadastradas e o dia da
        // semana;
        DiaLetivo diaLetivo = new DiaLetivo();
        diaLetivo.setAulas(aulasCadastradas);
        diaLetivo.setDiaSemana(diaLetivoRequest.getDia());

        // diaLetivo.getAulas().forEach(aula -> System.out.println(aula));

        // System.out.println(diaLetivo);

        DiaLetivo diaLetivoCriado = diaLetivoRepository.save(diaLetivo);

        // Retornando resposta HTTP com o DiaLetivo criado e sua respectiva URI de
        // acesso.
        return ResponseEntity.created(URI.create(URI_ENTIDADE_CRIADA + diaLetivoCriado.getId().toString()))
                .body(diaLetivoCriado);
    }

    @DeleteMapping("/{id}")
    public void deletarDiaLetivo(@PathVariable Long id) {
        diaLetivoRepository.deleteById(id);
    }

}
