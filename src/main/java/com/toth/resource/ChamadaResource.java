package com.toth.resource;

import com.toth.model.Aluno;
import com.toth.model.Chamada;
import com.toth.model.Turma;
import com.toth.model.dto.chamada.ChamadaPost;
import com.toth.repository.AlunoRepository;
import com.toth.repository.ChamadaRepository;
import com.toth.repository.TurmaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chamadas")
public class ChamadaResource {

    @Autowired
    private ChamadaRepository chamadaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("")
    public List<Chamada> getChamadas() {
        return chamadaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getChamadaById(@PathVariable Long id) {
        Optional<?> chamdaProcurada = chamadaRepository.findById(id);
        return chamdaProcurada.isPresent() ? ResponseEntity.ok(chamdaProcurada) : ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> chamadaCadastro(@RequestBody @Valid ChamadaPost chamada) {
        Long idTurma = chamada.getIdTurma();
        List<Long> idAlunos = chamada.getIdAlunos();

        Turma turma = turmaRepository.findById(idTurma).get();

        List<Aluno> alunos = alunoRepository.findAllById(idAlunos);

        Chamada chamadaObj = new Chamada();

        chamadaObj.setAlunos(alunos);
        chamadaObj.setTurma(turma);
        chamadaObj.setDiaEntrega(chamada.getDiaChamada());

        return ResponseEntity.ok().body(chamadaRepository.save(chamadaObj));
    }
}
