package com.matchwork.backend.controller;

import com.matchwork.backend.controller.request.VagaRequestDTO;
import com.matchwork.backend.controller.response.VagaResponseDTO;
import com.matchwork.backend.model.Competencia;
import com.matchwork.backend.model.CompetenciaVaga;
import com.matchwork.backend.model.Recrutador;
import com.matchwork.backend.model.Vaga;
import com.matchwork.backend.service.CandidatoService;
import com.matchwork.backend.service.CompetenciaService;
import com.matchwork.backend.service.RecrutadorService;
import com.matchwork.backend.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.matchwork.backend.controller.response.VagaMatchDTO;
import com.matchwork.backend.controller.response.CandidatoDTO;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recrutadores/{recrutadorId}/vagas")
@CrossOrigin(origins = "*")
public class VagaRecrutadorController {

    @Autowired
    private VagaService vagaService;

    @Autowired
    private RecrutadorService recrutadorService;

    @Autowired
    private CompetenciaService competenciaService;

    @Autowired
    private CandidatoService candidatoService;

    @PostMapping
    public ResponseEntity<VagaResponseDTO> cadastrarVaga(@PathVariable Long recrutadorId, @RequestBody VagaRequestDTO vaga) {
        if (vaga.getCompetencias().size() < 3) {
            throw new IllegalArgumentException("A vaga deve ter pelo menos 3 competências");
        }
        List<Competencia> competencias = competenciaService.buscarCompetencias(vaga.getCompetencias());
        if (competencias.size() < 3) {
            throw new IllegalArgumentException("A vaga deve ter pelo menos 3 competências");
        }
        Recrutador recrutador = recrutadorService.buscarRecrutador(recrutadorId);
        Vaga novaVaga = vaga.toEntity();
        novaVaga.setRecrutador(recrutador);
        novaVaga.setDataCriacao(OffsetDateTime.now());
        Set<CompetenciaVaga> competenciaVagas = competencias.stream().map(competencia -> {
            CompetenciaVaga competenciaVaga = new CompetenciaVaga();
            competenciaVaga.setCompetencia(competencia);
            competenciaVaga.setVaga(novaVaga);
            return competenciaVaga;
        }).collect(Collectors.toSet());
        novaVaga.setCompetenciasVagas(competenciaVagas);
        var vagaSalva = vagaService.criarVaga(novaVaga);
        competenciaService.associarCompetenciasVaga(novaVaga, competencias);
        var response = VagaResponseDTO.fromEntity(vagaSalva);
        response.setCompetencias(competencias.stream().map(Competencia::getNome).collect(Collectors.toSet()));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{vagaId}")
    public ResponseEntity<VagaResponseDTO> atualizarVaga(@PathVariable Long recrutadorId, @PathVariable Long vagaId, @RequestBody Vaga vaga) {
        Vaga vagaAtualizada = vagaService.atualizarVaga(recrutadorId, vagaId, vaga);
        return new ResponseEntity<>(VagaResponseDTO.fromEntity(vagaAtualizada), HttpStatus.OK);
    }

    @DeleteMapping("/{vagaId}")
    public ResponseEntity<Void> excluirVaga(@PathVariable Long recrutadorId, @PathVariable Long vagaId) {
        vagaService.excluirVaga(recrutadorId, vagaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{vagaId}/cancelar")
    public ResponseEntity<VagaResponseDTO> cancelarVaga(@PathVariable Long recrutadorId, @PathVariable Long vagaId) {
        Vaga vagaCancelada = vagaService.cancelarVaga(recrutadorId, vagaId);
        return new ResponseEntity<>(VagaResponseDTO.fromEntity(vagaCancelada), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VagaResponseDTO>> listarVagas(@PathVariable Long recrutadorId) {
        List<Vaga> vagas = vagaService.listarVagas(recrutadorId);
        return new ResponseEntity<>(vagas.stream().map(VagaResponseDTO::fromEntity).toList(), HttpStatus.OK);
    }

    @GetMapping("/{vagaId}")
    public ResponseEntity<VagaResponseDTO> consultarVaga(@PathVariable Long recrutadorId, @PathVariable Long vagaId) {
        Vaga vaga = vagaService.consultarVaga(recrutadorId, vagaId);
        List<Competencia> competencias = competenciaService.buscarCompetenciasVaga(vagaId);
        var response = VagaResponseDTO.fromEntity(vaga);
        response.setCompetencias(competencias.stream().map(Competencia::getNome).collect(Collectors.toSet()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/match")
    public ResponseEntity<List<VagaMatchDTO>> listarVagasComMatch(@PathVariable Long recrutadorId) {
        Recrutador recrutador = recrutadorService.buscarRecrutador(recrutadorId);
        List<Vaga> vagas = vagaService.listarVagas(recrutador.getId());
        List<VagaMatchDTO> resultado = new ArrayList<>();
        for (Vaga vaga : vagas) {
            List<CandidatoDTO> candidatosMatch = candidatoService.buscarCandidatosMatch(vaga)
                    .stream()
                    .map(candidato -> new CandidatoDTO(candidato.getId(), candidato.getNome()))
                    .collect(Collectors.toList());
            VagaMatchDTO vagaMatchDTO = new VagaMatchDTO();
            vagaMatchDTO.setId(vaga.getId());
            vagaMatchDTO.setTitulo(vaga.getTitulo());
            vagaMatchDTO.setMatchCandidatos(candidatosMatch);
            resultado.add(vagaMatchDTO);
        }
        return ResponseEntity.ok(resultado);
    }
}
