package br.com.academicwebapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class AlunoDisciplina {

    @JsonIgnore
    @EmbeddedId
    AlunoDisciplinaPK id;

    @ManyToOne
    @MapsId("id_aluno")
    @JoinColumn(name = "id_aluno")
    Aluno aluno;

    @ManyToOne
    @MapsId("id_disciplina")
    @JoinColumn(name = "id_disciplina")
    Disciplina disciplina;

    private double a1;
    private double a2;
    int faltas;

    public AlunoDisciplina() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AlunoDisciplina(AlunoDisciplinaPK id, Aluno aluno, Disciplina disciplina, double a1, double a2, int faltas) {
        super();
        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.a1 = a1;
        this.a2 = a2;
        this.faltas = faltas;
    }

    public AlunoDisciplina(AlunoDisciplinaPK id, Aluno aluno, Disciplina disciplina) {
        super();
        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
    }

    public AlunoDisciplinaPK getId() {
        return id;
    }

    public void setId(AlunoDisciplinaPK id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public double getA1() {
        return a1;
    }

    public void setA1(double a1) {
        this.a1 = a1;
    }

    public double getA2() {
        return a2;
    }

    public void setA2(double a2) {
        this.a2 = a2;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

}
