package com.matchwork.backend.controller.response;

import com.matchwork.backend.model.Recrutador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecrutadorResponseDTO {
    private Long id;
    private String nome;
    private String empresa;
    private String email;
    private String telefone;
    private String cnpj;
    private OffsetDateTime dataCriacao;

    public static RecrutadorResponseDTO fromEntity(Recrutador recrutador) {
        RecrutadorResponseDTO dto = new RecrutadorResponseDTO();
        dto.setId(recrutador.getId());
        dto.setNome(recrutador.getNome());
        dto.setEmpresa(recrutador.getEmpresa());
        dto.setEmail(recrutador.getEmail());
        dto.setTelefone(recrutador.getTelefone());
        dto.setCnpj(recrutador.getCnpj());
        dto.setDataCriacao(recrutador.getDataCriacao());
        return dto;
    }
}
