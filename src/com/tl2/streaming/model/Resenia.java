package com.tl2.streaming.model;

import java.time.LocalDateTime;

public class Resenia {
    private int calificacion;
    private String comentario;
    private int aprobado;
    private LocalDateTime fecha_hora;

    public int getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public int getAprobado() {
        return aprobado;
    }
    public void setAprobado(int aprobado) {
        this.aprobado = aprobado;
    }
    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }
    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    
}
