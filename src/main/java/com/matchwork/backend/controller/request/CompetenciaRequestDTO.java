package com.matchwork.backend.controller.request;

import com.matchwork.backend.model.Competencia;
import com.matchwork.backend.model.TipoCompetencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetenciaRequestDTO {
    private String nome;
    private String tipo;

    public Competencia toEntity() {
        return new Competencia(null, nome, TipoCompetencia.valueOf(tipo.trim().toUpperCase()));
    }

}
