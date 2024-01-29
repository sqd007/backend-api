package com.matchwork.backend.controller;

import com.matchwork.backend.dto.LoginResponseDTO;
import com.matchwork.backend.repository.UsuarioRepository;
import com.matchwork.backend.repository.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/{login}/{senha}")
    public LoginResponseDTO login(@PathVariable String login, @PathVariable String senha) {
        UserInfo usuario = usuarioRepository.findByLoginAndSenhaNative(login, senha);
        if (usuario != null) {
            return new LoginResponseDTO(usuario.getId(), usuario.getTipoUsuario(), true);
        } else {
            return new LoginResponseDTO(false);
        }
    }
}
