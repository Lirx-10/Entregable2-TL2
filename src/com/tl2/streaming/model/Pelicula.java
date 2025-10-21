package com.tl2.streaming.model;
import com.tl2.streaming.enumer.*;
public class Pelicula {
	private int id;
    private Generos genero;
    private String titulo;
    private String resumen;
    private String director;
    private double duracion;

    public Pelicula(){
		
	}
    
    public Pelicula(Generos genero, String titulo, String resumen, String director, double duracion){
        this.genero = genero;
        this.titulo = titulo;
        this.resumen = resumen;
        this.director = director;
        this.duracion = duracion;
    }

    public int getId() {
		return id;
	}
    public void setId(int id) {
    	this.id = id;
    }
    public Generos getGenero() {
        return genero;
    }
    public void setGenero(Generos genero) {
        this.genero = genero;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getResumen() {
        return resumen;
    }
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public double getDuracion() {
        return duracion;
    }
    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
}
