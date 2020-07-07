package com.toth.resource;

import com.toth.model.AutenticationRequest;
import com.toth.model.AuthenticationResponseProf;
import com.toth.model.Professor;
import com.toth.repository.ProfessorRepository;
import com.toth.service.ProfessorDetailService;
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
    private ProfessorDetailService professsorDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired(required = true)
    private PasswordEncoder passwordEncoder;

    @PostMapping("/autenticacao")
    public ResponseEntity<?> professorLogin(@RequestBody AutenticationRequest professorRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(professorRequest.getLogin(), professorRequest.getSenha()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponsesBody.BAD_LOGIN);
        }

        Professor professor = professorRepository.findByLogin(professorRequest.getLogin()).get();
        final UserDetails userDetails = professsorDetailsService.loadUserByUsername(professor.getLogin());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok().body(new AuthenticationResponseProf(professor, jwt));
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> setProfessor(@Valid @RequestBody Professor professor, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(ValidacoesFormat.formatarErros(bindingResult));

        if (professorRepository.existsByLogin(professor.getLogin()))
            return ResponseEntity.badRequest().body(ResponsesBody.LOGIN_CADASTRADO);
        if (professorRepository.existsByRg(professor.getRg()))
            return ResponseEntity.badRequest().body(ResponsesBody.RG_CADASTRADO);
        else {
            String senhaEncrypt = passwordEncoder.encode(professor.getSenha());
            professor.setSenha(senhaEncrypt);
            return ResponseEntity.ok(professorRepository.save(professor));
        }

    }
}
