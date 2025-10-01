package com.waira.v1.entity;

import com.waira.v1.entity.enums.PermisosAdmin;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Admins")
public class Admin extends Usuario {
    private PermisosAdmin[] permisos;
    
    public Admin() {
        super();
        this.setRol(com.waira.v1.entity.enums.Rol.ADMIN);
    }
    
    public PermisosAdmin[] getPermisos() {
        return permisos;
    }

    public void setPermisos(PermisosAdmin[] permisos) {
        this.permisos = permisos;
    }
}
