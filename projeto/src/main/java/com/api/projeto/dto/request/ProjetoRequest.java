package com.api.projeto.dto.request;

import com.api.projeto.entity.TipoProjeto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjetoRequest(
    @NotBlank @Size(max = 256) String nome,
    @NotBlank @Size(max = 7)   String codigo,
    @NotNull                   TipoProjeto tipoProjeto,
    @NotNull @Positive         BigDecimal valorHoraBase,
    @Positive                  BigDecimal horasContratadas,
    @NotNull                   LocalDate dataInicio,
    @NotNull                   LocalDate dataFim,
    @NotNull                   Integer responsavelId
) {}
