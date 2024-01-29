package com.matchwork.backend.controller;

import com.matchwork.backend.controller.request.RecrutadorRequestDTO;
import com.matchwork.backend.controller.response.RecrutadorResponseDTO;
import com.matchwork.backend.model.Recrutador;
import com.matchwork.backend.service.RecrutadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recrutadores")
@CrossOrigin(origins = "*")
public class RecrutadorController {

    @Autowired
    private RecrutadorService recrutadorService;

    @PostMapping
    public ResponseEntity<RecrutadorResponseDTO> criarRecrutador(@RequestBody RecrutadorRequestDTO recrutador) {
        Recrutador novoRecrutador = recrutadorService.criarRecrutador(recrutador.toEntity());
        return new ResponseEntity<>(RecrutadorResponseDTO.fromEntity(novoRecrutador), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecrutadorResponseDTO> buscarRecrutador(@PathVariable Long id) {
        Recrutador recrutador = recrutadorService.buscarRecrutador(id);
        return new ResponseEntity<>(RecrutadorResponseDTO.fromEntity(recrutador), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RecrutadorResponseDTO>> buscarRecrutadores() {
        return new ResponseEntity<>(
                recrutadorService.buscarRecrutadores()
                        .stream()
                        .map(RecrutadorResponseDTO::fromEntity)
                        .toList(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecrutadorResponseDTO> atualizarRecrutador(@PathVariable Long id, @RequestBody RecrutadorRequestDTO recrutador) {
        Recrutador recrutadorAtualizado = recrutadorService.atualizarRecrutador(id, recrutador.toEntity());
        return new ResponseEntity<>(RecrutadorResponseDTO.fromEntity(recrutadorAtualizado), HttpStatus.OK);
    }

    @PutMapping("/{id}/senha")
    public ResponseEntity<?> atualizarSenha(@PathVariable Long id, @RequestBody RecrutadorRequestDTO request) {
        recrutadorService.atualizarSenha(id, request.getSenha());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}