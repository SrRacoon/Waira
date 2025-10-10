package com.waira.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.waira.v1.entity.Usuario;
import com.waira.v1.entity.enums.Rol;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
    @GetMapping("/dashboard")
    public String getMethodName(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario != null && usuario.getRol() == Rol.PROVEEDOR) {
            return "dashboardProveedor";
        }

        return "redirect:/";
    }
    
}
