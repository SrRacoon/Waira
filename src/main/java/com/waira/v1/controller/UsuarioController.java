package com.waira.v1.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.waira.v1.dto.AdminRegisterDTO;
import com.waira.v1.dto.ClienteRegisterDTO;
import com.waira.v1.dto.LoginDTO;
import com.waira.v1.dto.ProveedorRegisterDTO;
import com.waira.v1.entity.Cliente;
import com.waira.v1.entity.Usuario;
import com.waira.v1.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto, HttpSession session) {
        try {
            Usuario usuarioLogueado = usuarioService.login(dto);
            session.setAttribute("usuarioLogueado", usuarioLogueado);

            String redirect = "/";
            switch (usuarioLogueado.getRol()) {
                case ADMIN -> redirect = "/temporal";
                case PROVEEDOR -> redirect = "/temporal";
                case CLIENTE -> redirect = "/temporal";
            }

            
            return ResponseEntity.ok(
                Map.of(
                    "success", true,
                    "role", usuarioLogueado.getRol().name(),
                    "redirect", redirect
                )
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                Map.of(
                    "success", false, 
                    "error", e.getMessage()
                )
            );
        }
    }

    @PostMapping("/register/cliente")
    public ResponseEntity<?> register(@RequestBody ClienteRegisterDTO dto, HttpSession session) {
        try {
            Cliente clienteNuevo = usuarioService.registerCliente(dto);
            session.setAttribute("usuarioLogueado", clienteNuevo);

            return ResponseEntity.ok(
                Map.of(
                    "success", true,
                    "role", clienteNuevo.getRol().name(),
                    "redirect", "/temporal"
                )
            );

        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(
                Map.of(
                    "success", false, 
                    "error", e.getMessage()
                )
            );
        }
    }

    @PostMapping("/register/proveedor")
    public ResponseEntity<?> register(@RequestBody ProveedorRegisterDTO dto, HttpSession session) {
        try {
            var proveedorNuevo = usuarioService.registerProveedor(dto);
            session.setAttribute("usuarioLogueado", proveedorNuevo);

            return ResponseEntity.ok(
                Map.of(
                    "success", true,
                    "role", proveedorNuevo.getRol().name(),
                    "redirect", "/temporal"
                )
            );

        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(
                Map.of(
                    "success", false, 
                    "error", e.getMessage()
                )
            );
        }
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> register(@RequestBody AdminRegisterDTO dto, HttpSession session) {
        try {
            var adminNuevo = usuarioService.registerAdmin(dto);
            session.setAttribute("usuarioLogueado", adminNuevo);

            return ResponseEntity.ok(
                Map.of(
                    "success", true,
                    "role", adminNuevo.getRol().name(),
                    "redirect", "/temporal"
                )
            );

        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(
                Map.of(
                    "success", false, 
                    "error", e.getMessage()
                )
            );
        }
    }

    @GetMapping("/delete-account")
    public String deleteAccount(HttpSession session, @RequestParam Long id) {
        usuarioService.deleteAccount(id);
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
}
