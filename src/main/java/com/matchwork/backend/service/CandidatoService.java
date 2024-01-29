package com.matchwork.backend.service;

import com.matchwork.backend.config.exception.NotFoundException;
import com.matchwork.backend.model.Candidato;
import com.matchwork.backend.model.Vaga;
import com.matchwork.backend.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    public Candidato criarCandidato(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    public Candidato atualizarCandidato(Long id, Candidato candidato) {
        Candidato candidatoAtual = candidatoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Candidato não encontrado"));
        candidatoAtual.setNome(candidato.getNome());
        candidatoAtual.setEmail(candidato.getEmail());
        candidatoAtual.setTelefone(candidato.getTelefone());
        return candidatoRepository.save(candidatoAtual);
    }

    public void atualizarSenha(Long id, String novaSenha) {
        Candidato candidatoAtual = candidatoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Candidato não encontrado"));
        candidatoAtual.setSenha(novaSenha);
        candidatoRepository.save(candidatoAtual);
    }

    public Candidato buscarCandidato(Long id) {
        return candidatoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Candidato não encontrado"));
    }

    public List<Candidato> buscarTodosCandidatos() {
        return candidatoRepository.findAll();

    }

    public List<Candidato> buscarCandidatosMatch(Vaga vaga) {
        return candidatoRepository.findCandidatosMatchedByVagaId(vaga.getId());
    }
}
