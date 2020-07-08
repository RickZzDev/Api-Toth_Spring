package com.toth.service;

import com.toth.model.Acesso;
import com.toth.model.Escola;
import com.toth.model.GenericUserDetails;
import com.toth.repository.AcessoRepository;
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
public class GenericUserDetailsService implements UserDetailsService {

    @Autowired
    AcessoRepository acessoRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Acesso> acesso = acessoRepository.findByLogin(s);

        acesso.orElseThrow(() -> new UsernameNotFoundException("User não encontrada: " + s));

        List<String> lista = new ArrayList<>();

        return acesso.map(GenericUserDetails::new).get();
    }

}
