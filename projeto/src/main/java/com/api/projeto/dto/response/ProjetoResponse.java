package com.api.projeto.dto.response;

import com.api.projeto.entity.TipoProjeto;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjetoResponse(
    Integer id,
    String nome,
    String codigo,
    TipoProjeto tipoProjeto,
    BigDecimal valorHoraBase,
    BigDecimal horasContratadas,  // null para alocacao
    BigDecimal valorTotal,        // valorHoraBase × horasContratadas, null para alocacao
    LocalDate dataInicio,
    LocalDate dataFim,
    Integer responsavelId
) {}
