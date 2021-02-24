package br.com.academicwebapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunosDisciplinaDto {

    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date nascimento;
    private String disciplina;

}
