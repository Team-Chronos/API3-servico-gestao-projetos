package com.api.projeto.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

// Representa o vínculo entre um usuário e um projeto, com seu valor/hora específico
@Entity
@Table(name = "projeto_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetoUsuario {

    @EmbeddedId
    private ProjetoUsuarioId id;

    @Column(name = "valor_hora", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorHora;
}
