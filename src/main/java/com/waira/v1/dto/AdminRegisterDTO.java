package com.waira.v1.dto;

import java.util.HashSet;
import java.util.Set;

import com.waira.v1.entity.enums.PermisosAdmin;

public class AdminRegisterDTO {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Set<PermisosAdmin> permisos = new HashSet<>();
    private String password;
    private String confirmPassword;

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public String getSurname() { 
        return surname; 
    }

    public void setSurname(String surname) { 
        this.surname = surname; 
    }

    public String getPhone() { 
        return phone; 
    }

    public void setPhone(String phone) { 
        this.phone = phone; 
    }

    public String getEmail() { 
        return email; 
    }

    public void setEmail(String email) { 
        this.email = email; 
    }

    public Set<PermisosAdmin> getPermisos() { 
        return permisos; 
    }

    public void setPermisos(Set<PermisosAdmin> permisos) { 
        this.permisos = permisos; 
    }
    
    public String getPassword() { 
        return password; 
    }

    public void setPassword(String password) { 
        this.password = password; 
    }

    public String getConfirmPassword() { 
        return confirmPassword; 
    }

    public void setConfirmPassword(String confirmPassword) { 
        this.confirmPassword = confirmPassword; 
    }
}
