package com.matchwork.backend.controller;

import com.matchwork.backend.controller.response.VagaResponseDTO;
import com.matchwork.backend.model.Vaga;
import com.matchwork.backend.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatos/{candidatoId}/vagas")
@CrossOrigin(origins = "*")
public class VagaCandidatoController {

    @Autowired
    private VagaService vagaService;

    @GetMapping("/match")
    public ResponseEntity<List<VagaResponseDTO>> getVagasMatchingCandidato(@PathVariable Long candidatoId) {
        List<Vaga> matchingVagas = vagaService.findVagasMatchingCandidato(candidatoId);
        return new ResponseEntity<>(matchingVagas
                .stream()
                .map(VagaResponseDTO::fromEntity)
                .toList(), HttpStatus.OK);
    }
}
