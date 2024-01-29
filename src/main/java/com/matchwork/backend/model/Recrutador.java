package com.matchwork.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "recrutadores")
@DiscriminatorValue("RECRUTADOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Recrutador extends Usuario {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String empresa;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String cnpj;

    @OneToMany(mappedBy = "recrutador", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vaga> vagas;


}