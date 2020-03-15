package com.toth.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.toth.model.Endereco;
import com.toth.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("")
    public List<Endereco> getEnderecos() {
        return enderecoRepository.findAll();
    }

}
