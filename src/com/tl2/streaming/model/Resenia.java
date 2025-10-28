package com.tl2.streaming.model;
import java.time.Instant;
import java.time.LocalDateTime;

public class Resenia {
	private int id;
    private int calificacion;
    private String comentario;
    private int aprobado;
    private LocalDateTime fecha_hora;
    private Usuario usuario;
    private Pelicula pelicula;

    public Resenia(){
    	
    }
    
    public int getId() {
		return id;
	}
    public void setId(int id) {
    	this.id = id;
    }
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	@Override
	public String toString() {
		return "Reseña ID = " + id + "\nCalificación = " + calificacion + "\nComentario = " + comentario + "\nAprobado = "
				+ aprobado + "\nFecha_hora = " + fecha_hora + "\nID_Usuario = " + this.usuario.getId() + "\npelicula = ID" + pelicula;
	}
}