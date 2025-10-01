package com.waira.v1.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.waira.v1.dto.AdminRegisterDTO;
import com.waira.v1.dto.LoginDTO;
import com.waira.v1.dto.ClienteRegisterDTO;
import com.waira.v1.dto.ProveedorRegisterDTO;
import com.waira.v1.entity.Admin;
import com.waira.v1.entity.Cliente;
import com.waira.v1.entity.Proveedor;
import com.waira.v1.entity.Usuario;
import com.waira.v1.entity.enums.Rol;
import com.waira.v1.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Usuario login(LoginDTO dto) {
        Usuario usuario = usuarioRepo.findByEmail(dto.getEmail());

        if (usuario == null || !usuario.getEstadoCuenta()) {
            throw new IllegalArgumentException("Esta cuenta no existe");
        }

        if (!usuario.getContraseña().equals(dto.getPassword())) {
            throw new IllegalArgumentException("Correo o contraseña incorrectos");
        }

        return usuario;
    }

    public Cliente registerCliente(ClienteRegisterDTO dto) {
        Usuario existente = usuarioRepo.findByEmail(dto.getEmail());

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        if (existente != null && existente.getEstadoCuenta()) {
            throw new IllegalArgumentException("Las correo ya está registrado");
        }

        if (existente != null && !existente.getEstadoCuenta() && existente.getRol() == Rol.CLIENTE) {
            existente.setNombre(dto.getName());
            existente.setApellido(dto.getSurname());
            existente.setTelefono(dto.getPhone());
            existente.setContraseña(dto.getPassword());
            existente.setEstadoCuenta(true);

            usuarioRepo.save(existente);
            return (Cliente) existente;
        }

        Cliente nuevo = new Cliente();
        nuevo.setNombre(dto.getName());
        nuevo.setApellido(dto.getSurname());
        nuevo.setTelefono(dto.getPhone());
        nuevo.setEmail(dto.getEmail());
        nuevo.setContraseña(dto.getPassword());
        nuevo.setEstadoCuenta(true);

        usuarioRepo.save(nuevo);
        
        return nuevo;
    }

    public Proveedor registerProveedor(ProveedorRegisterDTO dto) {
        Usuario existente = usuarioRepo.findByEmail(dto.getEmail());

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        if (existente != null && existente.getEstadoCuenta()) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        if (existente != null && !existente.getEstadoCuenta() && existente.getRol() == Rol.PROVEEDOR) {
            Proveedor proveedorExistente = (Proveedor) existente;
            proveedorExistente.setNombre(dto.getName());
            proveedorExistente.setApellido(dto.getSurname());
            proveedorExistente.setTelefono(dto.getPhone());
            proveedorExistente.setRazonSocial(dto.getCompanyName());
            proveedorExistente.setNit(dto.getNit());
            proveedorExistente.setDireccion(dto.getAddress());
            proveedorExistente.setContraseña(dto.getPassword());
            proveedorExistente.setEstadoCuenta(true);

            usuarioRepo.save(proveedorExistente);

            return proveedorExistente;
        }

        Proveedor nuevo = new Proveedor();
        nuevo.setNombre(dto.getName());
        nuevo.setApellido(dto.getSurname());
        nuevo.setTelefono(dto.getPhone());
        nuevo.setEmail(dto.getEmail());
        nuevo.setRazonSocial(dto.getCompanyName());
        nuevo.setNit(dto.getNit());
        nuevo.setDireccion(dto.getAddress());
        nuevo.setContraseña(dto.getPassword());
        nuevo.setRol(Rol.PROVEEDOR);
        nuevo.setEstadoCuenta(true);

        usuarioRepo.save(nuevo);

        return nuevo;
    }

    public Admin registerAdmin(AdminRegisterDTO dto) {
        Usuario existente = usuarioRepo.findByEmail(dto.getEmail());

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        if (existente != null && existente.getEstadoCuenta()) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        if (existente != null && !existente.getEstadoCuenta() && existente.getRol() == Rol.ADMIN) {
            Admin adminExistente = (Admin) existente;
            adminExistente.setNombre(dto.getName());
            adminExistente.setApellido(dto.getSurname());
            adminExistente.setTelefono(dto.getPhone());
            adminExistente.setPermisos(dto.getPermisos());
            adminExistente.setContraseña(dto.getPassword());
            adminExistente.setEstadoCuenta(true);

            usuarioRepo.save(adminExistente);

            return adminExistente;
        }

        Admin nuevo = new Admin();
        nuevo.setNombre(dto.getName());
        nuevo.setApellido(dto.getSurname());
        nuevo.setTelefono(dto.getPhone());
        nuevo.setEmail(dto.getEmail());
        nuevo.setPermisos(dto.getPermisos());
        nuevo.setContraseña(dto.getPassword());
        nuevo.setRol(Rol.ADMIN);
        nuevo.setEstadoCuenta(true);

        usuarioRepo.save(nuevo);

        return nuevo;
    }

    public Usuario deleteAccount(Long id) {
        Usuario usuario = usuarioRepo.findById(id).orElse(null);

        if (usuario != null) {
            usuario.setEstadoCuenta(false);
            usuarioRepo.save(usuario);
        }

        return usuario;
    }

}