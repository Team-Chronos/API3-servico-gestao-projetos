package com.api.projeto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

// Chave composta da tabela projeto_usuario
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoUsuarioId implements Serializable {

    @Column(name = "projeto_id")
    private Integer projetoId;

    @Column(name = "usuario_id")
    private Integer usuarioId;
}
