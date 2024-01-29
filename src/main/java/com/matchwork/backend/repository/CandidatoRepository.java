package com.matchwork.backend.repository;

import com.matchwork.backend.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    @Query("SELECT DISTINCT c " +
           "FROM Vaga v " +
           "JOIN CompetenciaVaga cv ON v.id = cv.vaga.id " +
           "JOIN CompetenciaCandidato cc ON cv.competencia.id = cc.competencia.id " +
           "JOIN Candidato c ON cc.candidato.id = c.id " +
           "WHERE v.id = :vagaId")
    List<Candidato> findCandidatosMatchedByVagaId(@Param("vagaId") Long vagaId);
}
