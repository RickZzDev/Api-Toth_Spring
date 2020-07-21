package com.toth.resource;

import com.toth.model.Acesso;
import com.toth.model.AutenticationRequest;
import com.toth.model.AuthenticationResponse;
import com.toth.model.Escola;
import com.toth.model.GenericUserDetails;
import com.toth.repository.AcessoRepository;
import com.toth.repository.EscolaRepository;

import com.toth.service.GenericUserDetailsService;
import com.toth.util.JwtUtil;
import com.toth.validations.ResponsesBody;
import com.toth.validations.ValidacoesFormat;

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

@RestController
@RequestMapping("/escolas")
@CrossOrigin
public class EscolaAutenticacaoResource {

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private EscolaRepository escolaRepository;

    // @Autowired
    // private Acesso acessoModel;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired(required = true)
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GenericUserDetailsService genericUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/autenticacao")

    public ResponseEntity<?> escolaLogin(@RequestBody AutenticationRequest acessoRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(acessoRequest.getLogin(), acessoRequest.getSenha()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponsesBody.BAD_LOGIN);
        }

        Acesso acesso = acessoRepository.findByLogin(acessoRequest.getLogin()).get();
        final UserDetails userDetails = genericUserDetailService.loadUserByUsername(acesso.getLogin());
        Escola escola = escolaRepository.findByAcesso(acesso);

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok().body(new AuthenticationResponse(escola, jwt));

    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> escolaCadastro(@RequestBody @Valid Escola escola, BindingResult bindingResult) {

        // return ResponseEntity.ok().body(escolaRepository.save(escola));
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(ValidacoesFormat.formatarErros(bindingResult));

        Acesso acesso = escola.getAcesso();
        if (acessoRepository.existsByLogin(acesso.getLogin()))
            return ResponseEntity.badRequest().body(ResponsesBody.LOGIN_CADASTRADO);

        else if (escolaRepository.existsByCnpj(escola.getCnpj()))
            return ResponseEntity.badRequest().body(ResponsesBody.CNPJ_CADASTRADO);
        else {
            Acesso login_senha = escola.getAcesso();
            String senhaEncrypt = passwordEncoder.encode(escola.getAcesso().getSenha());
            login_senha.setSenha(senhaEncrypt);
            escola.setAcesso(login_senha);

            UserDetails userDetails = new GenericUserDetails(acesso);

            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok().body(new AuthenticationResponse(escolaRepository.save(escola), jwt));
        }
    }

}
