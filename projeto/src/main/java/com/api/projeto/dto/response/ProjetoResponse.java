package com.api.projeto.dto.response;

import com.api.projeto.entity.TipoProjeto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjetoResponse(
    Integer id,
    String nome,
    String codigo,
    TipoProjeto tipoProjeto,
    BigDecimal valorHoraBase,
    BigDecimal horasContratadas,
    BigDecimal valorTotal,
    LocalDate dataInicio,
    LocalDate dataFim,
    Integer responsavelId
) implements Serializable {}