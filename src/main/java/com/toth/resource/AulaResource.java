package com.toth.resource;

import com.toth.model.*;
import com.toth.repository.AulaRepository;
import com.toth.repository.MateriaRepository;
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
@RequestMapping("/aulas")
public class AulaResource {

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    private List<Aula> getAulas(){return aulaRepository.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> getAulaById(@PathVariable Long id){
        Optional<?>aulaProcurada = aulaRepository.findById(id);
        return aulaProcurada.isPresent() ? ResponseEntity.ok().body(aulaProcurada) : ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<?> aulaCadastro(@RequestBody AulasRequest aulasRequest){
        // Id da matéria e do professor que foram recebidos na requisição
        Long idMateria = aulasRequest.getIdMateria();
        Long idProfessor = aulasRequest.getIdProfessor();

        // Buscando professor e materia com os id's recebidos na requisição
        Professor professor = professorRepository.findById(idProfessor).get();
        Materia materia = materiaRepository.findById(idMateria).get();

        // Criando uma aula com o professor e com a matéria que foram buscados com os id's da requisição
        Aula aula = new Aula(materia, professor);

        return ResponseEntity.ok().body(aulaRepository.save(aula));
    }

    @GetMapping("/professores/{idProfessor}")
    public ResponseEntity<?> listarAulasDoProfessor(@PathVariable Long idProfessor) {
        List<Turma> turmasCadastradas = turmaRepository.findAll();

        List<Aula> aulasMinistradasPeloProfessor = new ArrayList<>();

        for(Turma turma: turmasCadastradas) {
            List<DiaLetivo> diasLetivosDaTurma = turma.getCronograma().getDiasLetivos();

            for(DiaLetivo diaLetivo: diasLetivosDaTurma) {

                aulasMinistradasPeloProfessor = diaLetivo.getAulas();
                aulasMinistradasPeloProfessor.removeIf(aula -> aula.getProfessor().getId().equals(idProfessor));

            }

        }

        return ResponseEntity.ok().body(aulasMinistradasPeloProfessor);

    }

}
