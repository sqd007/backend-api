package com.matchwork.backend.controller;

import com.matchwork.backend.controller.request.CandidatoRequestDTO;
import com.matchwork.backend.controller.response.CandidatoResponseDTO;
import com.matchwork.backend.model.Candidato;
import com.matchwork.backend.model.Competencia;
import com.matchwork.backend.service.CandidatoService;
import com.matchwork.backend.service.CompetenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candidatos")
@CrossOrigin(origins = "*")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;
    @Autowired
    private CompetenciaService competenciaService;

    @PostMapping
    public ResponseEntity<CandidatoResponseDTO> criarCandidato(@RequestBody CandidatoRequestDTO candidato) {
        if(candidato.getCompetencias().size() < 3) {
            throw new IllegalArgumentException("O candidato deve ter pelo menos 3 competências");
        }
        List<Competencia> competencias = competenciaService.buscarCompetencias(candidato.getCompetencias());
        if(competencias.size() < 3) {
            throw new IllegalArgumentException("O candidato deve ter pelo menos 3 competências");
        }
        Candidato novoCandidato = candidatoService.criarCandidato(candidato.toEntity());
        competenciaService.associarCompetenciasCandidato(novoCandidato, competencias);
        var response = CandidatoResponseDTO.fromEntity(novoCandidato);
        response.setCompetencias(competencias.stream().map(Competencia::getNome).collect(Collectors.toSet()));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidatoResponseDTO> buscarCandidato(@PathVariable Long id) {
        Candidato candidato = candidatoService.buscarCandidato(id);
        var response = CandidatoResponseDTO.fromEntity(candidato);
        List<Competencia> competencias = competenciaService.buscarCompetenciasCandidato(id);
        response.setCompetencias(competencias.stream().map(Competencia::getNome).collect(Collectors.toSet()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CandidatoResponseDTO>> buscarTodosCandidatos() {
        return new ResponseEntity<>(
                candidatoService.buscarTodosCandidatos()
                        .stream()
                        .map(CandidatoResponseDTO::fromEntity)
                        .toList(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidatoResponseDTO> atualizarCandidato(@PathVariable Long id, @RequestBody CandidatoRequestDTO candidato) {
        Candidato candidatoAtualizado = candidatoService.atualizarCandidato(id, candidato.toEntity());
        return new ResponseEntity<>(CandidatoResponseDTO.fromEntity(candidatoAtualizado), HttpStatus.OK);
    }

    @PutMapping("/{id}/senha")
    public ResponseEntity<?> atualizarSenha(@PathVariable Long id, @RequestBody CandidatoRequestDTO request) {
        candidatoService.atualizarSenha(id, request.getSenha());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}