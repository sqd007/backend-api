package com.matchwork.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.matchwork.backend.model.Usuario;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "SELECT u.id_usuario as id, " +
            "CASE WHEN u.tipo_usuario = 'RECRUTADOR' THEN r.nome " +
            "WHEN u.tipo_usuario = 'CANDIDATO' THEN c.nome END as nome, " +
            "u.tipo_usuario as tipoUsuario " +
            "FROM usuarios u " +
            "LEFT JOIN recrutadores r ON u.id_usuario = r.id_usuario " +
            "LEFT JOIN candidatos c ON u.id_usuario = c.id_usuario " +
            "WHERE u.login = ?1 AND u.senha = ?2",
            nativeQuery = true)
    UserInfo findByLoginAndSenhaNative(String login, String senha);
}
