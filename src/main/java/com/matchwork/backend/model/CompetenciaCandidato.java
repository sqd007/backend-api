package com.matchwork.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "competencia_candidato")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CompetenciaCandidato {

    @EmbeddedId
    private CompetenciaCandidatoId id = new CompetenciaCandidatoId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("candidatoId")
    @JoinColumn(name = "id_candidato")
    private Candidato candidato;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("competenciaId")
    @JoinColumn(name = "id_competencia")
    private Competencia competencia;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CompetenciaCandidatoId implements Serializable {

        @Column(name = "id_candidato")
        private Long candidatoId;

        @Column(name = "id_competencia")
        private Long competenciaId;

    }
}