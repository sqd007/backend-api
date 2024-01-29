package com.matchwork.backend.controller.request;

import com.matchwork.backend.model.Candidato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoRequestDTO {
    private String nome;
    private String empresa;
    private String email;
    private String telefone;
    private String cpf;
    private String login;
    private String senha;
    private Set<Long> competencias = new HashSet<>();

    public Candidato toEntity() {
        Candidato candidato = new Candidato();
        candidato.setNome(this.nome);
        candidato.setEmail(this.email);
        candidato.setTelefone(this.telefone);
        candidato.setCpf(this.cpf);
        candidato.setLogin(this.login);
        candidato.setSenha(this.senha);
        candidato.setDataCriacao(OffsetDateTime.now());
        return candidato;
    }
}
