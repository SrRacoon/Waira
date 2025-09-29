package com.waira.v1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Proveedores")
public class Proveedor extends Usuario {
    private String razonSocial;
    private String nit;
    private String direccion;
    
    public Proveedor() {
        super();
        this.setRol(com.waira.v1.entity.enums.Rol.PROVEEDOR);
    }
    
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
