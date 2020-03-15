package com.toth.resource;

import com.toth.model.Escola;
import com.toth.repository.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
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
        return escolaProcurada.isPresent() ? ResponseEntity.ok(escolaProcurada) : ResponseEntity.notFound().build();
    }



    public static class ValidationErrorBuilder {

        public static ValidationError fromBindingErrors(Errors errors) {
            ValidationError error = new ValidationError("Erro de validação. " + errors.getErrorCount() + " error(s)");
            for (ObjectError objectError : errors.getAllErrors()) {
                error.addValidationError(objectError.getDefaultMessage());
            }
            return error;
        }
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> setEscola(@RequestBody @Valid Escola escola, BindingResult bindResult){
        if(bindResult.hasErrors()){
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(bindResult));
        }
        else {
            return  ResponseEntity.ok(escolaRepository.save(escola));
        }
    }

    //Testando validações**********************************************************
//    public ValidationError setEscola(@RequestBody @Valid Escola escola, BindingResult bindresult, Errors errors ) {
//        ValidationError error = new ValidationError("Validation failed. " + bindresult.getErrorCount() + " error(s)");
//        if(bindresult.hasFieldErrors()) {
//            for(ObjectError objError : bindresult.getAllErrors()){
//                error.addValidationError(objError.getDefaultMessage());
//            }
//            return error;
////            return ResponseEntity.badRequest().body(bindresult.getFieldError().getDefaultMessage());
//        }
//        else{
//            return  null;
////            return ResponseEntity.ok(escolaRepository.save(escola));
//        }
//    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Escola updateEscola(@Valid @RequestBody Escola escola) {
        return escolaRepository.save(escola);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEscola(@PathVariable Long id) {
        escolaRepository.deleteById(id);
    }

}
