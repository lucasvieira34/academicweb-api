package br.com.academicwebapi.service;

import br.com.academicwebapi.dto.AlunosDisciplinaDto;
import br.com.academicwebapi.dto.NotasDto;
import br.com.academicwebapi.models.*;
import br.com.academicwebapi.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    AlunoDisciplinaService alunoDisciplinaService;

    public Professor salvarProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarProfessorPorId(Long idProfessor) {
        return professorRepository.findById(idProfessor);
    }

    public BigDecimal totalSalarios() {
        return professorRepository.salarios();
    }

    public BigDecimal balanco() {
        return professorRepository.balanco();
    }

    public List<AlunosDisciplinaDto> alunosDasDisciplinas(Set<AlunoDisciplina> alunoDisciplina) {
        List<AlunosDisciplinaDto> alunos = alunoDisciplina.stream().map(x -> {
            var alunosDisciplinasDto = new AlunosDisciplinaDto();
            alunosDisciplinasDto.setId(x.getAluno().getId());
            alunosDisciplinasDto.setNome(x.getAluno().getNome());
            alunosDisciplinasDto.setSobrenome(x.getAluno().getSobrenome());
            alunosDisciplinasDto.setCpf(x.getAluno().getCpf());
            alunosDisciplinasDto.setNascimento(x.getAluno().getDataNascimento());
            alunosDisciplinasDto.setEmail(x.getAluno().getEmailResponsavel());
            alunosDisciplinasDto.setDisciplina(x.getDisciplina().getNome());
            return alunosDisciplinasDto;
        }).collect(Collectors.toList());
        return alunos;
    }


    public AlunoDisciplina inserirNotas(NotasDto notasDto, Long idAluno, Long idDisciplina, Optional<Aluno> aluno, Optional<Disciplina> disciplina) {
        var alunoDisciplinaPK = new AlunoDisciplinaPK(idAluno,idDisciplina);
        var alunoDisciplina = new AlunoDisciplina();
        alunoDisciplina.setId(alunoDisciplinaPK);
        alunoDisciplina.setAluno(aluno.get());
        alunoDisciplina.setDisciplina(disciplina.get());
        alunoDisciplina.setA1(notasDto.getA1());
        alunoDisciplina.setA2(notasDto.getA2());
        alunoDisciplina.setFaltas(notasDto.getFaltas());
        alunoDisciplinaService.salvarAlunoDisciplina(alunoDisciplina);
        return alunoDisciplina;
    }

    public Optional<Professor> buscarProfessorPeloCpf(String cpf) {
        return professorRepository.findByCpf(cpf);
    }
}
