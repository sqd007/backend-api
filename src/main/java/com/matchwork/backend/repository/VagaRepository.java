package com.matchwork.backend.repository;

import com.matchwork.backend.model.Competencia;
import com.matchwork.backend.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

    @Query("SELECT v FROM Vaga v WHERE v.id = :vagaId AND v.recrutador.id = :recrutadorId")
    Optional<Vaga> findByIdAndRecrutadorId(Long vagaId, Long recrutadorId);

    List<Vaga> findAllByRecrutadorId(Long recrutadorId);

    @Query("SELECT DISTINCT v FROM Vaga v " +
            "JOIN v.competenciasVagas cv " +
            "WHERE cv.competencia IN :competencias")
    List<Vaga> findVagasByCompetencias(@Param("competencias") List<Competencia> competencias);
}
