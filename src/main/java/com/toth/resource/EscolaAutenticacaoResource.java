package com.toth.resource;

import com.toth.model.AutenticationRequest;
import com.toth.model.AuthenticationResponse;
import com.toth.model.Escola;
import com.toth.model.EscolaDetails;
import com.toth.repository.EscolaRepository;

import com.toth.service.EscolaDetailsService;
import com.toth.util.JwtUtil;
import com.toth.validations.ResponsesBody;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/escolas")
@CrossOrigin
public class EscolaAutenticacaoResource {

    @Autowired
    private EscolaRepository escolaRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired(required = true)
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EscolaDetailsService escolaDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/autenticacao")

    public ResponseEntity<?> escolaLogin(@RequestBody AutenticationRequest escolaRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(escolaRequest.getLogin(), escolaRequest.getSenha()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponsesBody.BAD_LOGIN);
        }

        Escola escola = escolaRepository.findByLogin(escolaRequest.getLogin()).get();
        final UserDetails userDetails = escolaDetailsService.loadUserByUsername(escola.getLogin());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok().body(new AuthenticationResponse(escola, jwt));

    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> escolaCadastro(@RequestBody @Valid Escola escola, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(com.toth.validations.ValidacoesFormat.formatarErros(bindingResult));

        if(escolaRepository.existsByLogin(escola.getLogin()))
            return ResponseEntity.badRequest().body(com.toth.validations.ResponsesBody.LOGIN_CADASTRADO);
        else if(escolaRepository.existsByCnpj(escola.getCnpj()))
            return ResponseEntity.badRequest().body(com.toth.validations.ResponsesBody.CNPJ_CADASTRADO);
        else{
            String senhaEncrypt = passwordEncoder.encode(escola.getSenha());
            escola.setSenha(senhaEncrypt);
            return ResponseEntity.ok().body(escolaRepository.save(escola));
        }
    }

}
