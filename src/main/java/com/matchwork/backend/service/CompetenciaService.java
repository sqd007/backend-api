package com.matchwork.backend.service;

import com.matchwork.backend.model.*;
import com.matchwork.backend.repository.CompetenciaCandidatoRepository;
import com.matchwork.backend.repository.CompetenciaRepository;
import com.matchwork.backend.repository.CompetenciaVagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;
    @Autowired
    private CompetenciaCandidatoRepository competenciaCandidatoRepository;
    @Autowired
    private CompetenciaVagaRepository competenciaVagaRepository;

    public List<Competencia> criarCompetenciasEmLote(List<Competencia> competencias) {
        return competenciaRepository.saveAll(competencias);
    }

    public List<Competencia> listarCompetencias() {
        return competenciaRepository.findAll();
    }

    public List<Competencia> associarCompetenciasCandidato(Candidato candidato, List<Competencia> competencias) {
        List<CompetenciaCandidato> competenciaCandidatos = new ArrayList<>();
        competencias.forEach(competencia -> {
            CompetenciaCandidato competenciaCandidato = new CompetenciaCandidato();
            competenciaCandidato.setId(new CompetenciaCandidato.CompetenciaCandidatoId(candidato.getId(), competencia.getId()));
            competenciaCandidato.setCandidato(candidato);
            competenciaCandidato.setCompetencia(competencia);
            competenciaCandidatos.add(competenciaCandidato);
        });
        competenciaCandidatoRepository.saveAll(competenciaCandidatos);
        return competencias;
    }

    public List<Competencia> buscarCompetencias(Set<Long> competencias) {
        return competenciaRepository.findAllById(competencias);
    }

    public List<Competencia> buscarCompetenciasCandidato(Long idCandidato) {
        return competenciaRepository.buscarCompetenciasCandidato(idCandidato);
    }

    public List<Competencia> buscarCompetenciasVaga(Long idVaga) {
        return competenciaRepository.buscarCompetenciasVaga(idVaga);
    }

    public void associarCompetenciasVaga(Vaga vaga, List<Competencia> competencias) {
        List<CompetenciaVaga> competenciaVagas = new ArrayList<>();
        competencias.forEach(competencia -> {
            CompetenciaVaga competenciaVaga = new CompetenciaVaga();
            competenciaVaga.setId(new CompetenciaVaga.CompetenciaVagaId(vaga.getId(), competencia.getId()));
            competenciaVaga.setVaga(vaga);
            competenciaVaga.setCompetencia(competencia);
            competenciaVagas.add(competenciaVaga);
        });
        competenciaVagaRepository.saveAll(competenciaVagas);
    }
}
