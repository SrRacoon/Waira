package com.waira.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waira.v1.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
