package com.api.projeto.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProjetoUsuarioResponse(
    Integer projetoId,
    Integer usuarioId,
    BigDecimal valorHora
) implements Serializable {}