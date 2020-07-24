package com.toth.resource;

import com.toth.model.Acesso;
import com.toth.model.Aluno;
import com.toth.model.AutenticationRequest;
import com.toth.model.AuthenticationResponseAluno;
import com.toth.model.AuthenticationResponseProf;
import com.toth.model.Turma;
import com.toth.model.dto.aluno.AlunoDTO;
import com.toth.model.dto.aluno.AlunoPostDTO;
import com.toth.repository.AcessoRepository;
import com.toth.repository.AlunoRepository;
import com.toth.repository.TurmaRepository;
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

import java.lang.StackWalker.Option;
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

    @Autowired
    private TurmaRepository turmaRepository;

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
    public ResponseEntity<?> alunoCadastro(@RequestBody @Valid AlunoPostDTO aluno) {

        Acesso login_senha = aluno.getAcesso();
        String senhaEncrypt = passwordEncoder.encode(aluno.getAcesso().getSenha());
        login_senha.setSenha(senhaEncrypt);
        Turma turma = turmaRepository.findById(aluno.getIdTurma()).get();

        aluno.setTurma(turma);
        Optional<AlunoPostDTO> alunoOptional = Optional.of(aluno);

        Aluno aluno2 = alunoOptional.map(Aluno::new).get();

        aluno.setAcesso(login_senha);
        return ResponseEntity.ok().body(alunoRepository.save(aluno2));
    }

    @GetMapping("/turma/{idTurma}/lazy")
    @ResponseStatus(HttpStatus.OK)
    private List<AlunoDTO> getAlunoLazy(@PathVariable Long idTurma) {
        AlunoDTO teste = new AlunoDTO();
        Optional<Turma> turmaDesejada = turmaRepository.findById(idTurma);
        List<Aluno> alunos = alunoRepository.findByTurma(turmaDesejada.get());

        List<AlunoDTO> alunosAtualizados = teste.toAluno(alunos);

        return alunosAtualizados;

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
