package com.toth.resource;

import com.toth.model.*;
import com.toth.repository.AcessoRepository;
import com.toth.repository.ProfessorRepository;
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

@RestController()
@RequestMapping("/professores")
public class ProfessorAutenticacao {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired(required = true)
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private GenericUserDetailsService genericUserDetailService;

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
        Professor professor = professorRepository.findByAcesso(acesso);

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok().body(new AuthenticationResponseProf(professor, jwt));

    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> escolaCadastro(@RequestBody @Valid Professor professor, BindingResult bindingResult) {

        // return ResponseEntity.ok().body(escolaRepository.save(escola));
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(ValidacoesFormat.formatarErros(bindingResult));

        Acesso acesso = professor.getAcesso();
        if (acessoRepository.existsByLogin(acesso.getLogin()))
            return ResponseEntity.badRequest().body(ResponsesBody.LOGIN_CADASTRADO);

        else if (professorRepository.existsByRg(professor.getRg()))
            return ResponseEntity.badRequest().body(ResponsesBody.RG_CADASTRADO);
        else {
            Acesso login_senha = professor.getAcesso();
            String senhaEncrypt = passwordEncoder.encode(professor.getAcesso().getSenha());
            login_senha.setSenha(senhaEncrypt);
            professor.setAcesso(login_senha);

            UserDetails userDetails = new GenericUserDetails(acesso);

            String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok().body(new AuthenticationResponseProf(professorRepository.save(professor), jwt));
        }
    }
}
