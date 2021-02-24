package br.com.academicwebapi.service;

import br.com.academicwebapi.dto.ExtratoDto;
import br.com.academicwebapi.models.Aluno;
import br.com.academicwebapi.models.AlunoDisciplina;
import br.com.academicwebapi.models.AlunoDisciplinaPK;
import br.com.academicwebapi.models.Disciplina;
import br.com.academicwebapi.repository.AlunoRepository;
import br.com.academicwebapi.exceptions.AlunoExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    AlunoDisciplinaService alunoDisciplinaService;

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarAluno(Long id) {
        return alunoRepository.findById(id);
    }

    public Optional<Aluno> buscarAlunoPeloCpf(String cpf) {
        return alunoRepository.findByCpf(cpf);
    }

    public BigDecimal totalMensalidades() {
        return alunoRepository.mensalidades();
    }

    public List<ExtratoDto> alunoDisciplinas(Optional<Aluno> aluno, Set<AlunoDisciplina> alunoDisciplinas) {
        List<ExtratoDto> extratos = alunoDisciplinas.stream().map(x -> {
            var extrato = new ExtratoDto();
            extrato.setDisciplina(x.getDisciplina().getNome());
            extrato.setA1(x.getA1());
            extrato.setA2(x.getA2());
            extrato.setFaltas(x.getFaltas());
            return extrato;
        }).collect(Collectors.toList());
        return extratos;
    }
}
