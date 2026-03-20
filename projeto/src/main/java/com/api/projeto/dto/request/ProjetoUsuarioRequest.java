package com.api.projeto.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProjetoUsuarioRequest(
    @NotNull           Integer usuarioId,
    @NotNull @Positive BigDecimal valorHora
) {}
