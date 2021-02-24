package br.com.academicwebapi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricula;
    private String nome;
    private String sobrenome;
    private String cpf;
    @JsonFormat(pattern = "dd/MM/yyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    private BigDecimal salario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", unique = true)
    private Usuario usuario;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "ProfessoresDisciplinas",
               uniqueConstraints = @UniqueConstraint(columnNames = { "id_professor", "id_disciplina" }),
               joinColumns = @JoinColumn(name = "id_professor"),
               inverseJoinColumns = @JoinColumn(name = "id_disciplina"))
    private List<Disciplina> disciplinas;

}
