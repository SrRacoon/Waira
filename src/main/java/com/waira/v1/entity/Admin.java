package com.waira.v1.entity;

import java.util.HashSet;
import java.util.Set;

import com.waira.v1.entity.enums.PermisosAdmin;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Admins")
public class Admin extends Usuario {
    @ElementCollection(targetClass = PermisosAdmin.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "admin_permisos", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "permiso")
    private Set<PermisosAdmin> permisos = new HashSet<>();
    
    public Admin() {
        super();
        this.setRol(com.waira.v1.entity.enums.Rol.ADMIN);
    }
    
    public Set<PermisosAdmin> getPermisos() {
        return permisos;
    }

    public void setPermisos(Set<PermisosAdmin> permisos) {
        this.permisos = permisos;
    }
}
