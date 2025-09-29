package com.waira.v1.entity;

import com.waira.v1.entity.enums.PermisosAdmin;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Admins")
public class Admin extends Usuario {
    private PermisosAdmin[] permisos;
    
    public Admin(PermisosAdmin[] permisos) {
        super();
        this.setRol(com.waira.v1.entity.enums.Rol.ADMIN);
        this.permisos = permisos;
    }
    
    public PermisosAdmin[] getPermisos() {
        return permisos;
    }

    public void setPermisos(PermisosAdmin[] permisos) {
        this.permisos = permisos;
    }
}
