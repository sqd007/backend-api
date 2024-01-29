package com.matchwork.backend.controller;

import com.matchwork.backend.controller.request.CompetenciaRequestDTO;
import com.matchwork.backend.controller.response.CompetenciaResponseDTO;
import com.matchwork.backend.model.Competencia;
import com.matchwork.backend.service.CompetenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competencias")
@CrossOrigin(origins = "*")
public class CompetenciaController {

    @Autowired
    private CompetenciaService competenciaService;

    @PostMapping("/lote")
    public ResponseEntity<List<CompetenciaResponseDTO>> criarCompetenciasEmLote(@RequestBody List<CompetenciaRequestDTO> competenciasDTO) {
        List<Competencia> competenciasCriadas = competenciaService.criarCompetenciasEmLote(competenciasDTO.stream().map(CompetenciaRequestDTO::toEntity).collect(Collectors.toList()));
        return new ResponseEntity<>(competenciasCriadas.stream().map(CompetenciaResponseDTO::fromEntity).collect(Collectors.toList()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompetenciaResponseDTO>> listarCompetencias() {
        List<Competencia> competencias = competenciaService.listarCompetencias();
        return new ResponseEntity<>(competencias.stream().map(CompetenciaResponseDTO::fromEntity).collect(Collectors.toList()), HttpStatus.OK);
    }
}
