package com.matchwork.backend.repository;

import com.matchwork.backend.model.Competencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompetenciaRepository extends JpaRepository<Competencia, Long> {
    @Query("SELECT cc.competencia FROM CompetenciaCandidato cc WHERE cc.candidato.id = :idCandidato")
    List<Competencia> buscarCompetenciasCandidato(@Param("idCandidato") Long idCandidato);

    @Query("SELECT cv.competencia FROM CompetenciaVaga cv WHERE cv.vaga.id = :idVaga")
    List<Competencia> buscarCompetenciasVaga(@Param("idVaga") Long idVaga);
}
