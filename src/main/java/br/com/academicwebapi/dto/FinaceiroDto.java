package br.com.academicwebapi.dto;

import br.com.academicwebapi.models.StatusFinanceiro;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinaceiroDto {

    private BigDecimal receita;
    private BigDecimal despesa;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal balanco;
    private StatusFinanceiro status;

}
