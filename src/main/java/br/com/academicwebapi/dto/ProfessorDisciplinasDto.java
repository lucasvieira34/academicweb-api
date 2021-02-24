package br.com.academicwebapi.dto;

import br.com.academicwebapi.models.Disciplina;
import br.com.academicwebapi.models.Professor;
import lombok.Data;

import java.util.List;

@Data
public class ProfessorDisciplinasDto {

    private Professor professor;
    private List<Disciplina> disciplinas;

}
