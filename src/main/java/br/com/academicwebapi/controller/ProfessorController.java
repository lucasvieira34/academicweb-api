package br.com.academicwebapi.controller;

import br.com.academicwebapi.dto.NotasDto;
import br.com.academicwebapi.exceptions.AlunoNaoEncontradoException;
import br.com.academicwebapi.exceptions.ProfessorNaoMinistraDisciplinaException;
import br.com.academicwebapi.models.*;
import br.com.academicwebapi.service.*;
import br.com.academicwebapi.dto.AlunosDisciplinaDto;
import br.com.academicwebapi.dto.FinaceiroDto;
import br.com.academicwebapi.dto.ProfessorDisciplinasDto;
import br.com.academicwebapi.exceptions.DisciplinaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    DisciplinaService disciplinaService;

    @Autowired
    ProfessorService professorService;

    @Autowired
    AlunoService alunoService;

    @Autowired
    UsuarioService usuarioService;

    private Usuario usuarioLogado;

    // LISTA DE ALUNOS DAS DISCPLINAS
    @GetMapping(path = "/{disciplina}/alunos")
    public ResponseEntity<List<AlunosDisciplinaDto>> alunosDasDisciplinas(@PathVariable("disciplina") String nomeDisciplina) {
        Optional<Disciplina> disciplina = disciplinaService.buscarDisciplinaNome(nomeDisciplina);
        if(!disciplina.isPresent()) {
            throw new DisciplinaNaoEncontradaException("A disciplina não foi encontrada.");
        }
        Set<AlunoDisciplina> alunoDisciplina = disciplina.get().getExtratos();
        List<AlunosDisciplinaDto> alunos = professorService.alunosDasDisciplinas(alunoDisciplina);
        return ResponseEntity.ok(alunos);
    }

    // INSERIR NOTAS DO ALUNO NA DISCIPLINA
    @PutMapping(path = "/aluno/{id_aluno}/disciplina/{id_disciplina}")
    public ResponseEntity<AlunoDisciplina> inserirNotas(@RequestBody NotasDto notasDto,
                                                        @PathVariable("id_aluno") Long idAluno,
                                                        @PathVariable("id_disciplina") Long idDisciplina) {

        usuarioLogado();
        Optional<Aluno> aluno = alunoService.buscarAluno(idAluno);
        if(!aluno.isPresent()) {
            throw new AlunoNaoEncontradoException("O aluno não foi encontrado.");
        }
        Optional<Disciplina> disciplina = disciplinaService.buscarDisciplina(idDisciplina);
        if(!disciplina.isPresent()) {
            throw new DisciplinaNaoEncontradaException("A disciplina não foi encontrada.");
        }

        Optional<Professor> professor = professorService.buscarProfessorPorId(usuarioLogado.getProfessor().getId());
        List<Disciplina> disciplinasProf = professor.get().getDisciplinas();

        if(disciplinasProf.contains(disciplina.get())){
            AlunoDisciplina alunoDisciplina = professorService.inserirNotas(notasDto, idAluno, idDisciplina, aluno, disciplina);
            return ResponseEntity.ok().body(alunoDisciplina);
        } else {
            throw new ProfessorNaoMinistraDisciplinaException("Você não ministra essa disciplina.");
        }

    }

    private void usuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String login = autenticado.getName();
            usuarioLogado = usuarioService.usuarioPorLogin(login);
        }
    }

}
