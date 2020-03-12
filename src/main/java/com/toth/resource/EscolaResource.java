package com.toth.resource;

import com.toth.model.Escola;
import com.toth.repository.EnderecoRepository;
import com.toth.repository.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/escolas")
public class EscolaResource {

    @Autowired
    private EscolaRepository escolaRepository;

    @GetMapping("")
    public List<Escola> getEscolas() {
        return escolaRepository.findAll();
    }

    @PostMapping("")
    public void setEscola(@RequestBody Escola escola) {
        escolaRepository.save(escola);
    }

}
