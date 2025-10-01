package com.waira.v1.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.waira.v1.entity.enums.EstadoPublicacion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Publicaciones")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fechaPublicacion;
    private LocalDateTime fechaExpiracion;
    private LocalDateTime fechaUltimaActualizacion;
    private int visitas;
    private int reservasGeneradas;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;

    @Enumerated(EnumType.STRING)
    private EstadoPublicacion estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public LocalDateTime getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(LocalDateTime fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }

    public int getReservasGeneradas() {
        return reservasGeneradas;
    }

    public void setReservasGeneradas(int reservasGeneradas) {
        this.reservasGeneradas = reservasGeneradas;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }
    
    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public EstadoPublicacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPublicacion estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Publicacion{");
        sb.append("id=").append(id);
        sb.append(", fechaPublicacion=").append(fechaPublicacion);
        sb.append(", fechaExpiracion=").append(fechaExpiracion);
        sb.append(", fechaUltimaActualizacion=").append(fechaUltimaActualizacion);
        sb.append(", visitas=").append(visitas);
        sb.append(", reservasGeneradas=").append(reservasGeneradas);
        sb.append(", servicio=").append(servicio);
        sb.append(", estado=").append(estado);
        sb.append('}');
        return sb.toString();
    }
}
