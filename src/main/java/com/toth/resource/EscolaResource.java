package com.toth.resource;

import com.toth.model.Professor;
import com.toth.model.dto.escola.EscolaCNPJDTO;
import com.toth.model.Escola;
import com.toth.model.dto.escola.EscolaDTO;
import com.toth.repository.EscolaRepository;
import com.toth.validations.ResponsesBody;
import com.toth.validations.ValidacoesFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/escolas")
public class EscolaResource {

    @Autowired
    private EscolaRepository escolaRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Escola> getEscolas() {
        return escolaRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getEscolaById(@PathVariable Long id) {
        Optional<?> escolaProcurada = escolaRepository.findById(id);
        return escolaProcurada.isPresent() ? ResponseEntity.ok(escolaProcurada)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponsesBody.ESCOLA_NOT_FOUND);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateEscola(@Valid @RequestBody Escola escola, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(ValidacoesFormat.formatarErros(bindingResult));

        return ResponseEntity.ok(escolaRepository.save(escola));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEscola(@PathVariable Long id) {
        if (escolaRepository.existsById(id)) {
            escolaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponsesBody.ESCOLA_NOT_FOUND);
    }

    @CrossOrigin
    @PostMapping("/cnpj")
    public ResponseEntity<?> validationCnpj(@Valid @RequestBody EscolaCNPJDTO escolaCnpj, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(ResponsesBody.CNPJ_INVALIDO);

        if (escolaRepository.existsByCnpj(escolaCnpj.getCnpj()))
            return ResponseEntity.badRequest().body(ResponsesBody.CNPJ_CADASTRADO);

        return ResponseEntity.ok().body(ResponsesBody.CNPJ_VALIDO);

    }

    @PostMapping("/{id}")
    public ResponseEntity<?> atualizarEscola(@PathVariable Long id, @Valid @RequestBody EscolaDTO escolaDTO,
            BindingResult bindingResult) {

        System.out.println(escolaDTO);

        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(ValidacoesFormat.formatarErros(bindingResult));

        Optional<Escola> escolaOp = escolaRepository.findById(id);

        if (!escolaOp.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponsesBody.ESCOLA_NOT_FOUND);

        Escola escolaAtualizada = escolaDTO.toEscola(escolaOp.get());

        return ResponseEntity.ok(escolaRepository.save(escolaAtualizada));

    }

    @GetMapping("/{id}/professores")
    public ResponseEntity<?> getProfessoresDaEscola(@PathVariable Long id) {
        Optional<Escola> escola = escolaRepository.findById(id);

        if (!escola.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponsesBody.RECURSO_NOT_FOUND);

        return ResponseEntity.ok().body(escola.get().getProfessores());
    }

    @PostMapping("/{id}/cadastrar-professor")
    public ResponseEntity<?> cadastrarProfessorNaEscola(@PathVariable Long id, @RequestBody Professor professor) {
        Optional<Escola> opEscola = escolaRepository.findById(id);

        if (!opEscola.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponsesBody.RECURSO_NOT_FOUND);

        Escola escola = opEscola.get();

        escola.addProfessor(professor);

        return ResponseEntity.ok().body(escolaRepository.save(escola));
    }

}
