package com.api.projeto.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "projeto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 256)
    private String nome;

    // Código único de identificação do projeto (ex: "PRJ0001")
    @Column(nullable = false, length = 7, unique = true)
    private String codigo;

    @Column(name = "tipo_projeto", nullable = false)
    @Convert(converter = TipoProjetoConverter.class)
    private TipoProjeto tipoProjeto;

    @Column(name = "valor_hora_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorHoraBase;

    // Apenas para hora_fechada. Nulo para alocacao
    @Column(name = "horas_contratadas", precision = 10, scale = 2)
    private BigDecimal horasContratadas;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    // ID do usuário responsável (gerente do projeto)
    @Column(name = "responsavel_id", nullable = false)
    private Integer responsavelId;
}
