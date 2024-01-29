package com.matchwork.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "candidato_processo_seletivo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CandidatoProcessoSeletivo {

    @EmbeddedId
    private CandidatoProcessoSeletivoId id;

    @ManyToOne
    @MapsId("idProcessoSeletivo")
    @JoinColumn(name = "id_processo_seletivo")
    private ProcessoSeletivo processoSeletivo;

    @Enumerated(EnumType.STRING)
    private ResultadoProcesso resultado;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CandidatoProcessoSeletivoId implements Serializable {
        private Long idCandidato;
        private Long idProcessoSeletivo;
    }
}
