package br.com.academicwebapi.repository;

import br.com.academicwebapi.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByCpf(String cpf);

    @Query("select sum(mensalidade) from Aluno")
    BigDecimal mensalidades();

}
