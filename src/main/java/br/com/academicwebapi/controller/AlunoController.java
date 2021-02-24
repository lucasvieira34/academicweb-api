package br.com.academicwebapi.controller;

import br.com.academicwebapi.models.*;
import br.com.academicwebapi.service.AlunoDisciplinaService;
import br.com.academicwebapi.service.AlunoService;
import br.com.academicwebapi.service.DisciplinaService;
import br.com.academicwebapi.dto.ExtratoDto;
import br.com.academicwebapi.dto.NotasDto;
import br.com.academicwebapi.exceptions.AlunoNaoEncontradoException;
import br.com.academicwebapi.exceptions.DisciplinaNaoEncontradaException;
import br.com.academicwebapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @Autowired
    DisciplinaService disciplinaService;

    @Autowired
    AlunoDisciplinaService alunoDisciplinaService;

    @Autowired
    UsuarioService usuarioService;

    private Usuario usuarioLogado;

    // LISTA DE DISCIPLINAS DE UM ALUNO
    @GetMapping(path = "/aluno/disciplinas")
    public ResponseEntity<List<ExtratoDto>> alunoDisciplinas() {
        usuarioLogado();
        Optional<Aluno> aluno = alunoService.buscarAluno(usuarioLogado.getAluno().getId());
        if(!aluno.isPresent()) {
            throw new AlunoNaoEncontradoException("O aluno não foi encontrado.");
        }
        Set<AlunoDisciplina> alunoDisciplinas = aluno.get().getExtratos();
        List<ExtratoDto> extratos = alunoService.alunoDisciplinas(aluno, alunoDisciplinas);
        return ResponseEntity.ok().body(extratos);
    }

    private void usuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String login = autenticado.getName();
            usuarioLogado = usuarioService.usuarioPorLogin(login);
        }
    }

}
