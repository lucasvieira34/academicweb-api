package br.com.academicwebapi.repository;

import br.com.academicwebapi.models.Aluno;
import br.com.academicwebapi.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByCpf(String cpf);

    @Query("select sum(salario) from Professor")
    BigDecimal salarios();
    // realocar para secretaria

    @Query("SELECT (SUM(mensalidade) - (SELECT SUM(salario) FROM Professor)) FROM Aluno")
    BigDecimal balanco();
}
