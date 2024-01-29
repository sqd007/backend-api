package com.matchwork.backend.controller.request;

import com.matchwork.backend.model.StatusVaga;
import com.matchwork.backend.model.Vaga;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VagaRequestDTO {
    private String titulo;
    private String descricao;
    private Set<Long> competencias = new HashSet<>();

    public Vaga toEntity() {
        Vaga vaga = new Vaga();
        vaga.setTitulo(this.titulo);
        vaga.setDescricao(this.descricao);
        vaga.setStatus(StatusVaga.ABERTA);
        vaga.setDataCriacao(OffsetDateTime.now());
        return vaga;
    }
}
