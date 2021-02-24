package br.com.academicwebapi.controller;

import br.com.academicwebapi.service.CadastroProfessorService;
import br.com.academicwebapi.dto.CadastroAlunoDto;
import br.com.academicwebapi.dto.CadastroProfessorDto;
import br.com.academicwebapi.dto.FinaceiroDto;
import br.com.academicwebapi.exceptions.AlunoNaoEncontradoException;
import br.com.academicwebapi.models.Aluno;
import br.com.academicwebapi.models.Disciplina;
import br.com.academicwebapi.models.Professor;
import br.com.academicwebapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/secretaria")
public class SecretariaController {

    @Autowired
    SecretariaService secretariaService;

    @Autowired
    ProfessorService professorService;

    @Autowired
    DisciplinaService disciplinaService;

    @Autowired
    AlunoService alunoService;

    @Autowired
    CadastroAlunoService cadastroAlunoService;

    @Autowired
    CadastroProfessorService cadastroProfessorService;

    // RELATORIO FINANCEIRO
    @GetMapping(path = "/financeiro")
    public ResponseEntity<FinaceiroDto> relatorioFinanceiro() {
        FinaceiroDto financeiroDto = secretariaService.relatorioFinaceiro();
        return ResponseEntity.ok(financeiroDto);
    }

    // LISTA DE TODAS AS DISCIPLINAS
    @GetMapping("/disciplinas")
    public List<Disciplina> listarDisciplinas() {
        return disciplinaService.findAll();
    }

    // LISTA DE TODOS OS ALUNOS
    @GetMapping(path = "/alunos")
    public List<Aluno> listarAlunos() {
        return alunoService.findAll();
    }

    // BUSCAR ALUNO POR ID
    @GetMapping(path = "/aluno/{id}")
    public ResponseEntity<Aluno> listarAluno(@PathVariable("id") Long id) {
        Optional<Aluno> aluno = alunoService.buscarAluno(id);

        if(!aluno.isPresent()) {
            throw new AlunoNaoEncontradoException("O aluno não foi encontrado.");
        }
        return ResponseEntity.ok(aluno.get());
    }

    // LISTA DE TODOS OS PROFESSORES
    @GetMapping(path = "/professores")
    public List<Professor> listarTodosProfessores() {
        return professorService.findAll();
    }

    // CADASTRO DE PROFESSOR
    @PostMapping(path = "/cadastrar/professor")
    public ResponseEntity<CadastroProfessorDto> salvarProfessor(@RequestBody CadastroProfessorDto professorDto) {
        professorDto = cadastroProfessorService.salvarProfessor(professorDto);
        Optional<Professor> professor = professorService.buscarProfessorPeloCpf(professorDto.getCpf());
        //corrigir url de consulta
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(professor.get().getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // CADASTRO DE ALUNO
    @PostMapping(path = "/cadastrar/aluno")
    public ResponseEntity<CadastroAlunoDto> salvarAluno(@RequestBody CadastroAlunoDto alunoDto) {
        alunoDto = cadastroAlunoService.salvarAluno(alunoDto);
        Optional<Aluno> aluno = alunoService.buscarAlunoPeloCpf(alunoDto.getCpf());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(aluno.get().getId()).toUri();
        //corrigir url de consulta
        return ResponseEntity.created(uri).build();
    }

    // LISTA DE DISCIPLINAS DOS PROFESSORES
    @GetMapping(path = "professor/disciplinas/{id_professor}")
    public ResponseEntity<List<Disciplina>> listaDisciplinas(@PathVariable("id_professor") Long idProfessor) {
        Optional<Professor> professor = professorService.buscarProfessorPorId(idProfessor);
        List<Disciplina> disciplinas = professor.get().getDisciplinas();
        return ResponseEntity.ok(disciplinas);
    }

    // ASSOCIAR PROFESSOR À DISCIPLINA
    @PutMapping(path = "/professor/{id_professor}/disciplina/{id_disciplina}")
    public ResponseEntity<Stream> associarDisciplina(@PathVariable("id_professor") Long idProfessor,
                                                     @PathVariable("id_disciplina") Long idDisciplina) {

        Optional<Professor> professor = professorService.buscarProfessorPorId(idProfessor);
        Optional<Disciplina> disciplina = disciplinaService.buscarDisciplina(idDisciplina);
        Stream<Object> dtoProf = secretariaService.associarDisciplinaProfessor(professor, disciplina);
        return ResponseEntity.ok(dtoProf);
    }

}
