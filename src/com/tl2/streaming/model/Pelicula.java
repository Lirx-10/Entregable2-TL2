package com.tl2.streaming.model;
import com.tl2.streaming.enumer.*;
public class Pelicula {

    private Generos genero;
    private String titulo;
    private String resumen;
    private String director;
    private int duracion;

    public Pelicula(Generos genero, String titulo, String resumen, String director, int duracion){
        this.genero = genero;
        this.titulo = titulo;
        this.resumen = resumen;
        this.director = director;
        this.duracion = duracion;
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
    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
