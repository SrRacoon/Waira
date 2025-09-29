package com.waira.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.waira.v1.dto.LoginDTO;
import com.waira.v1.dto.ClienteRegisterDTO;
import com.waira.v1.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto, HttpSession session) {
        return usuarioService.login(dto, session);
    }

    @PostMapping("/register/cliente")
    public ResponseEntity<?> register(@RequestBody ClienteRegisterDTO dto, HttpSession session) {
        return usuarioService.registerCliente(dto, session);
    }

    @GetMapping("/delete-account")
    public String deleteAccount(HttpSession session, @RequestParam Long id) {
        usuarioService.deleteAccount(id, session);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        usuarioService.logout(session);
        return "redirect:/";
    }
}
