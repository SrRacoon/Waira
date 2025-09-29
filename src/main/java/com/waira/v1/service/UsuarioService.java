package com.waira.v1.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.waira.v1.dto.LoginDTO;
import com.waira.v1.dto.ClienteRegisterDTO;
import com.waira.v1.dto.ProveedorRegisterDTO;
import com.waira.v1.entity.Cliente;
import com.waira.v1.entity.Proveedor;
import com.waira.v1.entity.Usuario;
import com.waira.v1.entity.enums.Rol;
import com.waira.v1.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public ResponseEntity<?> login(LoginDTO loginDto, HttpSession session) {
        Usuario user = usuarioRepo.findByEmail(loginDto.getEmail());

        if (user == null || !user.getEstadoCuenta()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Esa cuenta no existe"));
        }

        if (user.getContraseña().equals(loginDto.getPassword())) {
            session.setAttribute("usuarioLogueado", user);

            String redirect;
            switch (user.getRol()) {
                case ADMIN -> redirect = "/admin/dashboard";
                case CLIENTE -> redirect = "/temporal";
                case PROVEEDOR -> redirect = "/temporal";
                default -> redirect = "/temporal";
            }

            return ResponseEntity.ok(Map.of(
                "success", true,
                "role", user.getRol().name(),
                "redirect", redirect
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Correo o contraseña incorrectos"));
        }
    }

    public ResponseEntity<?> registerCliente(ClienteRegisterDTO registerDto, HttpSession session) {
        Usuario existente = usuarioRepo.findByEmail(registerDto.getEmail());

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Las contraseñas no coinciden"));
        }

        if (existente != null && existente.getEstadoCuenta()) {
            return ResponseEntity.badRequest().body(Map.of("error", "El correo ya está registrado"));
        }

        if (existente != null && !existente.getEstadoCuenta()) {
            existente.setNombre(registerDto.getName());
            existente.setApellido(registerDto.getSurname());
            existente.setTelefono(registerDto.getPhone());
            existente.setContraseña(registerDto.getPassword());
            existente.setEstadoCuenta(true);

            usuarioRepo.save(existente);
            session.setAttribute("usuarioLogueado", existente);

            return ResponseEntity.ok(Map.of(
                "success", true,
                "role", existente.getRol().name(),
                "redirect", "/temporal"
            ));
        }

        Usuario nuevo = new Cliente();
        nuevo.setNombre(registerDto.getName());
        nuevo.setApellido(registerDto.getSurname());
        nuevo.setTelefono(registerDto.getPhone());
        nuevo.setEmail(registerDto.getEmail());
        nuevo.setContraseña(registerDto.getPassword());
        nuevo.setEstadoCuenta(true);

        usuarioRepo.save(nuevo);
        session.setAttribute("usuarioLogueado", nuevo);

        return ResponseEntity.ok(Map.of(
            "success", true,
            "role", nuevo.getRol().name(),
            "redirect", "/temporal"
        ));
    }

    public ResponseEntity<?> registerProveedor(ProveedorRegisterDTO registerDto, HttpSession session) {
        Usuario existente = usuarioRepo.findByEmail(registerDto.getEmail());

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Las contraseñas no coinciden"));
        }

        if (existente != null && existente.getEstadoCuenta()) {
            return ResponseEntity.badRequest().body(Map.of("error", "El correo ya está registrado"));
        }

        if (existente != null && !existente.getEstadoCuenta() && existente.getRol() == Rol.PROVEEDOR) {
            Proveedor proveedorExistente = (Proveedor) existente;
            proveedorExistente.setNombre(registerDto.getName());
            proveedorExistente.setApellido(registerDto.getSurname());
            proveedorExistente.setTelefono(registerDto.getPhone());
            proveedorExistente.setRazonSocial(registerDto.getCompanyName());
            proveedorExistente.setNit(registerDto.getNit());
            proveedorExistente.setDireccion(registerDto.getAddress());
            proveedorExistente.setContraseña(registerDto.getPassword());
            proveedorExistente.setEstadoCuenta(true);

            usuarioRepo.save(proveedorExistente);
            session.setAttribute("usuarioLogueado", proveedorExistente);

            return ResponseEntity.ok(Map.of(
                "success", true,
                "role", proveedorExistente.getRol().name(),
                "redirect", "/proveedor/menu"
            ));
        }

        Proveedor nuevo = new Proveedor();
        nuevo.setNombre(registerDto.getName());
        nuevo.setApellido(registerDto.getSurname());
        nuevo.setTelefono(registerDto.getPhone());
        nuevo.setEmail(registerDto.getEmail());
        nuevo.setRazonSocial(registerDto.getCompanyName());
        nuevo.setNit(registerDto.getNit());
        nuevo.setDireccion(registerDto.getAddress());
        nuevo.setContraseña(registerDto.getPassword());
        nuevo.setRol(Rol.PROVEEDOR);
        nuevo.setEstadoCuenta(true);

        usuarioRepo.save(nuevo);
        session.setAttribute("usuarioLogueado", nuevo);

        return ResponseEntity.ok(Map.of(
            "success", true,
            "role", nuevo.getRol().name(),
            "redirect", "/proveedor/menu"
        ));
    }

    public void deleteAccount(Long id, HttpSession session) {
        Usuario user = usuarioRepo.findById(id).orElse(null);
        if (user != null) {
            user.setEstadoCuenta(false);
            usuarioRepo.save(user);
            session.invalidate();
        }
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }

}