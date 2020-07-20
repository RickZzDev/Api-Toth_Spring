package com.toth.resource;

import com.toth.model.Acesso;
import com.toth.model.Aluno;
import com.toth.model.AutenticationRequest;
import com.toth.model.AuthenticationResponseAluno;
import com.toth.model.AuthenticationResponseProf;
import com.toth.repository.AcessoRepository;
import com.toth.repository.AlunoRepository;
import com.toth.service.GenericUserDetailsService;
import com.toth.util.JwtUtil;
import com.toth.validations.ResponsesBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

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

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    private List<Aluno> getAlunos() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlunoById(@PathVariable Long id) {
        Optional<?> alunoProcurado = alunoRepository.findById(id);
        return alunoProcurado.isPresent() ? ResponseEntity.ok(alunoProcurado) : ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> alunoCadastro(@RequestBody @Valid Aluno aluno) {

        Acesso login_senha = aluno.getAcesso();
        String senhaEncrypt = passwordEncoder.encode(aluno.getAcesso().getSenha());
        login_senha.setSenha(senhaEncrypt);
        aluno.setAcesso(login_senha);
        return ResponseEntity.ok().body(alunoRepository.save(aluno));
    }

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
        Aluno aluno = alunoRepository.findByAcesso(acesso);

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok().body(new AuthenticationResponseAluno(aluno, jwt));

    }
}
