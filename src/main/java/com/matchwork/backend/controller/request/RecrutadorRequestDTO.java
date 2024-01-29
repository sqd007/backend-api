package com.matchwork.backend.controller.request;

import com.matchwork.backend.model.Recrutador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecrutadorRequestDTO {
    private String nome;
    private String empresa;
    private String email;
    private String telefone;
    private String cnpj;
    private String login;
    private String senha;

    public Recrutador toEntity() {
        Recrutador recrutador = new Recrutador();
        recrutador.setNome(this.nome);
        recrutador.setEmpresa(this.empresa);
        recrutador.setEmail(this.email);
        recrutador.setTelefone(this.telefone);
        recrutador.setCnpj(this.cnpj);
        recrutador.setLogin(this.login);
        recrutador.setSenha(this.senha);
        recrutador.setDataCriacao(OffsetDateTime.now());
        return recrutador;
    }
}
