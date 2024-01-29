package com.matchwork.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private Long id;
    private String tipoUsuario;
    private boolean autorizado;

    public LoginResponseDTO(boolean autorizado) {
        this.autorizado = autorizado;
    }
}
