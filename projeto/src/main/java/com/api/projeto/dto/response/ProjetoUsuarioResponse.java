package com.api.projeto.dto.response;

import java.math.BigDecimal;

public record ProjetoUsuarioResponse(
    Integer projetoId,
    Integer usuarioId,
    BigDecimal valorHora
) {}
