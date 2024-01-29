package com.matchwork.backend.service;

import com.matchwork.backend.config.exception.NotFoundException;
import com.matchwork.backend.model.Competencia;
import com.matchwork.backend.model.StatusVaga;
import com.matchwork.backend.model.Vaga;
import com.matchwork.backend.repository.CompetenciaCandidatoRepository;
import com.matchwork.backend.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private CompetenciaService competenciaService;
    public Vaga criarVaga(Vaga vaga) {
        return vagaRepository.save(vaga);
    }

    public Vaga atualizarVaga(Long recrutadorId, Long vagaId, Vaga vaga) {
        Vaga vagaAtual = consultarVaga(vagaId, recrutadorId);
        vagaAtual.setTitulo(vaga.getTitulo());
        vagaAtual.setDescricao(vaga.getDescricao());
        return vagaRepository.save(vagaAtual);
    }

    public void excluirVaga(Long recrutadorId, Long vagaId) {
        Vaga vaga = consultarVaga(vagaId, recrutadorId);
        vagaRepository.delete(vaga);
    }

    public Vaga consultarVaga(Long recrutadorId, Long vagaId) {
        return vagaRepository.findByIdAndRecrutadorId(vagaId, recrutadorId)
                .orElseThrow(() -> new NotFoundException("Vaga não encontrada"));
    }

    public List<Vaga> listarVagas(Long recrutadorId) {
        return vagaRepository.findAllByRecrutadorId(recrutadorId);
    }

    public Vaga cancelarVaga(Long recrutadorId, Long vagaId) {
        Vaga vaga = consultarVaga(recrutadorId, vagaId);
        vaga.setStatus(StatusVaga.CANCELADA);
        return vagaRepository.save(vaga);
    }

    public List<Vaga> findVagasMatchingCandidato(Long candidatoId) {
        List<Competencia> competenciasCandidato = competenciaService.buscarCompetenciasCandidato(candidatoId);
        List<Vaga> matchingVagas = vagaRepository.findVagasByCompetencias(competenciasCandidato);

        return matchingVagas;
    }
}
