package com.toth.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.toth.model.Aluno;
import com.toth.model.Nota;
import com.toth.model.Prova;
import com.toth.model.dto.nota.Counter;
import com.toth.model.dto.nota.ListNotas;
import com.toth.model.dto.nota.NotaByAluno;
import com.toth.model.dto.nota.NotaDTO;
import com.toth.repository.AlunoRepository;
import com.toth.repository.NotaRepository;
import com.toth.repository.ProvaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/notas")
public class NotaResource {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProvaRepository provaRepository;

    @GetMapping("")
    public List<Nota> getNotas() {
        return notaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotaById(@PathVariable Long id) {
        Optional<?> notaProcurada = notaRepository.findById(id);
        return notaProcurada.isPresent() ? ResponseEntity.ok(notaProcurada) : ResponseEntity.notFound().build();
    }

    @GetMapping("/aluno/{idAluno}")
    public ResponseEntity<?> getNotaByIdAluno(@PathVariable Long idAluno) {

        List<Prova> provas = provaRepository.findAll();
        Aluno alunoReq = alunoRepository.findById(idAluno).get();

        List<ListNotas> provasNotas = new ArrayList<>();
        List<NotaByAluno> notasResponse = new ArrayList<>();

        provas.forEach(prova -> {
            List<Nota> notas = notaRepository.findByProva(prova);
            NotaByAluno newNotas = new NotaByAluno();

            Counter counter = new Counter();
            counter.setCounter(0);
            notas.forEach(nota -> {

                if (nota.getAluno().getId() == alunoReq.getId()) {
                    newNotas.setNota(nota.getValor());
                    newNotas.setNomeProva(prova.getNome());
                } else {

                    counter.setCounter(nota.getValor() + counter.getCounter());
                }

            });
            newNotas.setNotaGeral(counter.getCounter());

            notasResponse.add(newNotas);
            ;

        });

        return ResponseEntity.ok(notasResponse);
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> notaCadastro(@RequestBody @Valid List<NotaDTO> notaDTOs) {
        notaDTOs.forEach(nota -> {
            Nota newNota = new Nota();
            Aluno alunoProcurado = alunoRepository.findById(nota.getAlunoDTO().getId()).get();
            Prova provaProcurada = provaRepository.findById(nota.getIdProva()).get();

            newNota.setValor(nota.getValor());
            newNota.setAluno(alunoProcurado);
            newNota.setProva(provaProcurada);

            notaRepository.save(newNota);
        });
        return ResponseEntity.ok().build();
    }
}
