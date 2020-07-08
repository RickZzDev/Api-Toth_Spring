package com.toth.service;

import com.toth.model.Acesso;
import com.toth.model.Escola;
import com.toth.model.GenericUserDetails;
import com.toth.model.Professor;
import com.toth.model.ProfessorDetail;
import com.toth.repository.AcessoRepository;
import com.toth.repository.EscolaRepository;
import com.toth.repository.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// ProfessorDetailService implements UserDetailsService

@Service
public class ProfessorDetailService implements UserDetailsService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    private AcessoRepository acessoRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Acesso> acesso = acessoRepository.findByLogin(s);

        acesso.orElseThrow(() -> new UsernameNotFoundException("Professor não encontrado: " + s));

        List<String> lista = new ArrayList<>();

        return acesso.map(GenericUserDetails::new).get();
    }

}
