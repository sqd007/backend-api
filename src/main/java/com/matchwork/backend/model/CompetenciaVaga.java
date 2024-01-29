package com.matchwork.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "competencia_vaga")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CompetenciaVaga {

    @EmbeddedId
    private CompetenciaVagaId id = new CompetenciaVagaId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vagaId")
    @JoinColumn(name = "id_vaga")
    private Vaga vaga;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("competenciaId")
    @JoinColumn(name = "id_competencia")
    private Competencia competencia;


    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CompetenciaVagaId implements Serializable {

        @Column(name = "id_vaga")
        private Long vagaId;

        @Column(name = "id_competencia")
        private Long competenciaId;
    }
}