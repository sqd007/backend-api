package com.matchwork.backend.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.matchwork.backend.model.Vaga;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.Set;

@Getter
@Setter
public class VagaResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;

    @JsonProperty("status")
    private String statusVaga;

    @JsonProperty("data_criacao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataCriacao;

    @JsonProperty("empresa_recrutadora")
    private String empresaRecrutadora;

    @JsonProperty("email_recrutador")
    private String emailRecrutador;

    @JsonProperty("nome_recrutador")
    private String nomeRecrutador;

    private Set<String> competencias;

    public static VagaResponseDTO fromEntity(Vaga vaga) {
        VagaResponseDTO dto = new VagaResponseDTO();
        dto.setId(vaga.getId());
        dto.setTitulo(vaga.getTitulo());
        dto.setDescricao(vaga.getDescricao());
        dto.setStatusVaga(vaga.getStatus().name());
        dto.setDataCriacao(vaga.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        if (vaga.getRecrutador() != null) {
            dto.setEmpresaRecrutadora(vaga.getRecrutador().getEmpresa());
            dto.setEmailRecrutador(vaga.getRecrutador().getEmail());
            dto.setNomeRecrutador(vaga.getRecrutador().getNome());
        }
        Set<String> competencias = vaga.getCompetenciasVagas().stream().map(competenciaVaga -> competenciaVaga.getCompetencia().getNome()).collect(java.util.stream.Collectors.toSet());
        dto.setCompetencias(competencias);
        return dto;
    }
}