package br.com.academicwebapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer codigo;
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "disciplina")
    Set<AlunoDisciplina> extratos;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "ProfessoresDisciplinas",
            uniqueConstraints = @UniqueConstraint(columnNames = { "id_professor", "id_disciplina" }),
            joinColumns = @JoinColumn(name = "id_disciplina"),
            inverseJoinColumns = @JoinColumn(name = "id_professor")
    )
    private List<Professor> professores;

}
