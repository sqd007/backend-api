package com.matchwork.backend.controller.response;

import com.matchwork.backend.model.Candidato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Set<String> competencias;

    public CandidatoResponseDTO(Long id, String nome, String email, String telefone, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public static CandidatoResponseDTO fromEntity(Candidato candidato) {
        return new CandidatoResponseDTO(
                candidato.getId(),
                candidato.getNome(),
                candidato.getEmail(),
                candidato.getTelefone(),
                candidato.getCpf()
        );
    }
}
