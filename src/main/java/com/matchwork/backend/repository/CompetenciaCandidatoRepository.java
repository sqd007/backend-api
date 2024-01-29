package com.matchwork.backend.repository;

import com.matchwork.backend.model.CompetenciaCandidato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenciaCandidatoRepository extends JpaRepository<CompetenciaCandidato, CompetenciaCandidato.CompetenciaCandidatoId> {
}
