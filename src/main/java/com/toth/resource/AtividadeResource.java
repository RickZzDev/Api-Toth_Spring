package com.toth.resource;

import java.util.Optional;

import com.toth.model.AlternativasQuestao;
import com.toth.model.Aluno;
import com.toth.model.Atividade;
import com.toth.model.Aula;
import com.toth.model.Turma;
import com.toth.model.dto.atividade.AlternativasAtividades;
import com.toth.model.dto.atividade.AtividadeDTO;
import com.toth.repository.AlunoRepository;
import com.toth.repository.AtividadeRepository;
import com.toth.repository.AulaRepository;
import com.toth.repository.TurmaRepository;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@RestController
@RequestMapping("/atividades")
public class AtividadeResource {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

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

    @PutMapping("/enviar-resposta")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateAlunosAtividade(@RequestBody Atividade atividade) {

        return ResponseEntity.ok().body(atividadeRepository.save(atividade));

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

    @PostMapping("/pontos")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getNota(@RequestBody AlternativasAtividades alternativasAtividades) {

        Atividade atividadeProcurada = atividadeRepository.findById(alternativasAtividades.getIdAtividade()).get();

        Aluno alunoProcurado = alunoRepository.findById(alternativasAtividades.getIdAluno()).get();

        ArrayList<String> alternativasCertas = new ArrayList<>();

        List<String> alternativasEnviadas = new ArrayList<>();

        int acertos = 0;

        atividadeProcurada.getQuestoes().forEach(questao -> {
            if (questao.getTipo().equals("Dissertativa")) {
            } else {
                questao.getAlternativasQuestao().forEach(questaoME -> {
                    if (questaoME.getCorreto()) {
                        alternativasCertas.add(questaoME.getAlternativa());
                    }
                });
            }
        });

        alternativasAtividades.getAlternativas().forEach(alternativa -> {
            alternativasEnviadas.add(alternativa);
        });

        for (int i = 0; i < alternativasCertas.size(); i++) {
            if (alternativasEnviadas.get(i).equals(alternativasCertas.get(i))) {
                acertos = +1;
            } else {
            }
        }

        List<Aluno> alunosCompletaram = atividadeProcurada.getAlunosCompletaram();
        alunosCompletaram.add(alunoProcurado);
        atividadeProcurada.setAlunosCompletaram(alunosCompletaram);

        updateAlunosAtividade(atividadeProcurada);

        return ResponseEntity.ok().body(new JSONObject().put("Acertos", acertos).toString());

    }

    @GetMapping("/professor/{idProfessor}")
    public ResponseEntity<?> listarAtividadesProfessor(@PathVariable Long idProfessor) {
        List<Atividade> atividadesCadastradas = atividadeRepository.findAll();

        List<Atividade> atividadesDoProfessor = new ArrayList<>();

        atividadesCadastradas.forEach(atividade -> {

            if (atividade.getAulas().getProfessor().getId().equals(idProfessor)) {
                atividadesDoProfessor.add(atividade);

            }

        });

        return ResponseEntity.ok().body(atividadesDoProfessor);
    }

    @GetMapping("/alunos/{idAluno}")
    public ResponseEntity<?> listarAtividadesByAluno(@PathVariable Long idAluno) {
        List<Atividade> atividadesCadastradas = atividadeRepository.findAll();

        Aluno alunoAsking = alunoRepository.findById(idAluno).get();

        List<Atividade> atividadesNaoFeitas = new ArrayList<>();

        atividadesCadastradas.forEach(atividade -> {

            if (atividade.getAlunosCompletaram().isEmpty()) {
                atividadesNaoFeitas.add(atividade);
            } else {
                atividade.getAlunosCompletaram().forEach(aluno -> {
                    if (aluno.getId().equals(idAluno)) {

                    } else {
                        atividadesNaoFeitas.add(atividade);

                    }
                });
            }

        });

        return ResponseEntity.ok().body(atividadesNaoFeitas);
    }

}