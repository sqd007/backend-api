package com.matchwork.backend.service;

import com.matchwork.backend.config.exception.NotFoundException;
import com.matchwork.backend.model.Recrutador;
import com.matchwork.backend.repository.RecrutadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecrutadorService {

    @Autowired
    private RecrutadorRepository recrutadorRepository;

    public Recrutador criarRecrutador(Recrutador recrutador) {
        return recrutadorRepository.save(recrutador);
    }

    public Recrutador atualizarRecrutador(Long id, Recrutador recrutador) {
        Recrutador recrutadorAtualizado = recrutadorRepository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException("Recrutador não encontrado"));
        recrutadorAtualizado.setNome(recrutador.getNome());
        recrutadorAtualizado.setEmail(recrutador.getEmail());
        recrutadorAtualizado.setTelefone(recrutador.getTelefone());
        recrutadorAtualizado.setEmpresa(recrutador.getEmpresa());
        recrutadorAtualizado.setSenha(recrutador.getSenha());
        recrutadorAtualizado.setVagas(recrutador.getVagas());
        return recrutadorRepository.save(recrutadorAtualizado);
    }

    public void atualizarSenha(Long id, String novaSenha) {
        Recrutador recrutador = recrutadorRepository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException("Recrutador não encontrado"));
        recrutador.setSenha(novaSenha);
        recrutadorRepository.save(recrutador);
    }

    public Recrutador buscarRecrutador(Long id) {
        return recrutadorRepository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException("Recrutador não encontrado"));
    }

    public List<Recrutador> buscarRecrutadores() {
        return recrutadorRepository.findAll();
    }
}
