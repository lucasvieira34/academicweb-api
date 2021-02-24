package br.com.academicwebapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDisciplinaPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_aluno")
    Long idAluno;

    @Column(name = "id_disciplina")
    Long idDisciplina;

}
