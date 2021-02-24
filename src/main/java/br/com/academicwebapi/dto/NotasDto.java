package br.com.academicwebapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotasDto {

    private double a1;
    private double a2;
    private int faltas;

}
