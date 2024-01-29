package com.matchwork.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "processo_seletivo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessoSeletivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_candidato")
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "id_vaga")
    private Vaga vaga;

    @Enumerated(EnumType.STRING)
    private StatusProcessoSeletivo status;

    @OneToMany(mappedBy = "processoSeletivo")
    private Set<CandidatoProcessoSeletivo> candidatosInscritos;

}