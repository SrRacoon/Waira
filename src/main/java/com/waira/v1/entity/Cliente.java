package com.waira.v1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clientes")
public class Cliente extends Usuario {

    public Cliente() {
        super();
        this.setRol(com.waira.v1.entity.enums.Rol.CLIENTE);
    }
    

}
