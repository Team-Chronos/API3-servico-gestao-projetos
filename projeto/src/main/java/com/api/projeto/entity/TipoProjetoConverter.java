package com.api.projeto.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoProjetoConverter implements AttributeConverter<TipoProjeto, String> {

    @Override
    public String convertToDatabaseColumn(TipoProjeto attribute) {
        if (attribute == null) return null;
        return switch (attribute) {
            case ALOCACAO -> "alocacao";
            case HORA_FECHADA -> "hora_fechada";
        };
    }

    @Override
    public TipoProjeto convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "alocacao" -> TipoProjeto.ALOCACAO;
            case "hora_fechada" -> TipoProjeto.HORA_FECHADA;
            default -> throw new IllegalArgumentException("Tipo de projeto desconhecido: " + dbData);
        };
    }
}
