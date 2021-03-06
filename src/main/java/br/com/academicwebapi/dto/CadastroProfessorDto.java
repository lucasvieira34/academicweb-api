package br.com.academicwebapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CadastroProfessorDto {

    private String matricula;
    private String nome;
    private String sobrenome;
    private String cpf;
    @JsonFormat(pattern = "dd/MM/yyy")
    private Date dataNascimento;
    private BigDecimal salario;
    private String email;
    private String login;
    private String senha;

}
