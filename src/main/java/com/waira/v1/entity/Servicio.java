package com.waira.v1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Servicios")
public class Servicio {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private Double precio;
    private String categoria;
    private String ubicacion;
    private int dias_duracion;
    
    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getDias_duracion() {
        return dias_duracion;
    }

    public void setDias_duracion(int dias_duracion) {
        this.dias_duracion = dias_duracion;
    }

    public Proveedor getProveedor_id() {
        return proveedor_id;
    }

    public void setProveedor_id(Proveedor proveedor_id) {
        this.proveedor_id = proveedor_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Servicio{");
        sb.append("id=").append(id);
        sb.append(", titulo=").append(titulo);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", precio=").append(precio);
        sb.append(", categoria=").append(categoria);
        sb.append(", ubicacion=").append(ubicacion);
        sb.append(", dias_duracion=").append(dias_duracion);
        sb.append(", proveedor_id=").append(proveedor_id);
        sb.append('}');
        return sb.toString();
    }
}
