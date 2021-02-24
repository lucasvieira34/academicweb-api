package br.com.academicwebapi.service;

import br.com.academicwebapi.dto.FinaceiroDto;
import br.com.academicwebapi.dto.ProfessorDisciplinasDto;
import br.com.academicwebapi.models.Disciplina;
import br.com.academicwebapi.models.Professor;
import br.com.academicwebapi.models.StatusFinanceiro;
import br.com.academicwebapi.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class SecretariaService {

    @Autowired
    ProfessorService professorService;

    @Autowired
    AlunoService alunoService;

    @Autowired
    ProfessorRepository professorRepository;

    public FinaceiroDto relatorioFinaceiro() {
        BigDecimal totalMensalidades = alunoService.totalMensalidades();
        BigDecimal totalSalarios = professorService.totalSalarios();
        BigDecimal balanco = professorService.balanco();

        var financeiroDto = new FinaceiroDto();

        if (balanco.signum() > 0) {
            financeiroDto.setStatus(StatusFinanceiro.SUPERAVIT);
        } else {
            financeiroDto.setStatus(StatusFinanceiro.DEFICT);
        }
        financeiroDto.setReceita(totalMensalidades);
        financeiroDto.setDespesa(totalSalarios);
        financeiroDto.setBalanco(balanco);

        return financeiroDto;
    }

    public Stream<Object> associarDisciplinaProfessor(Optional<Professor> professor, Optional<Disciplina> disciplina) {
        professor.get().getDisciplinas().add(disciplina.get());
        professorService.salvarProfessor(professor.get());

        Stream<Object> dtoProf = professor.stream().map(x -> {
            var dto = new ProfessorDisciplinasDto();
            dto.setProfessor(professor.get());
            dto.setDisciplinas(professor.get().getDisciplinas());
            return dto;
        });
        return dtoProf;
    }
}
