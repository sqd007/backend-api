package com.matchwork.backend.controller.response;

import com.matchwork.backend.model.Competencia;
import com.matchwork.backend.model.TipoCompetencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetenciaResponseDTO {

    private Long id;
    private String nome;
    private String tipo;

    public static CompetenciaResponseDTO fromEntity(Competencia competencia) {
        return new CompetenciaResponseDTO(competencia.getId(),
                competencia.getNome(),
                competencia.getTipo().toString());
    }
}
