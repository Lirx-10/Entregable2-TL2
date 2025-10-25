package com.tl2.streaming.model;
import java.time.Instant;

public class Resenia {
	private int id;
    private int calificacion;
    private String comentario;
    private int aprobado;
    private Instant fecha_hora;
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
    public Instant getFecha_hora() {
        return fecha_hora;
    }
    public void setFecha_hora(Instant fecha_hora) {
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
		return "Resenia [id=" + id + ", calificacion=" + calificacion + ", comentario=" + comentario + ", aprobado="
				+ aprobado + ", fecha_hora=" + fecha_hora + ",\npelicula=\n" + pelicula + "]";
	}
}