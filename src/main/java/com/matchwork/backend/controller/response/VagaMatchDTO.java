package com.matchwork.backend.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VagaMatchDTO {

    private Long id;
    private String titulo;
    private List<CandidatoDTO> matchCandidatos;

}
