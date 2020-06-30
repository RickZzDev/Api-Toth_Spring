package com.toth.service;

import com.toth.model.Escola;
import com.toth.model.EscolaDetails;
import com.toth.repository.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EscolaDetailsService implements UserDetailsService {

    @Autowired
    EscolaRepository escolaRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Escola> escola = escolaRepository.findByLogin(s);

        escola.orElseThrow(() -> new UsernameNotFoundException("Escola não encontrada: " + s));

        List<String> lista = new ArrayList<>();

        return escola.map(EscolaDetails::new).get();
    }

}
