package com.tl2.streaming.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.tl2.streaming.dao.FactoryDAO;
import com.tl2.streaming.dao.PeliculaDAO;
import com.tl2.streaming.enumer.Generos;
import com.tl2.streaming.model.Pelicula;
import com.tl2.streaming.util.comparators.*;

public class UtilidadesPelicula {
	private PeliculaDAO peliculaDAO;
	
	public UtilidadesPelicula() {
		peliculaDAO = FactoryDAO.getPeliculaDAO();
	}
	public List<Pelicula> obtenerPeliculas(){
		return peliculaDAO.obtenerTodo();
	}
	public List<Pelicula> ordenarPeliculas(List<Pelicula> peliculas) {
		Comparator<Pelicula> comparador = null;
		Scanner sc = new Scanner(System.in);
    	System.out.println("Seleccione el criterio de ordenamiento:");
    	System.out.println("1. Título");
    	System.out.println("2. Duración");
    	System.out.println("3. Género");
    	System.out.println("Otro número para salir");
    	int opcion = sc.nextInt();
    	sc.close();
		switch(opcion) {
		case 1:
			comparador = new ComparadorPorTitulo();
			break;
		case 2:
			comparador = new ComparadorPorDuracion();
			break;
		case 3:
			comparador = new ComparadorPorGenero();
			break;
		default:
			System.out.println("Saliendo...");
			return null;
		}
		Collections.sort(peliculas, comparador);
		return peliculas;
    }
	public void mostrarPeliculas(List<Pelicula> peliculas) {
		System.out.println("Películas:");
		for(Pelicula p : peliculas) {
			System.out.println(p);
		}
	}
    public void registrarPelicula() {
    	Scanner sc = new Scanner(System.in);
    	Pelicula nue = new Pelicula();
		System.out.println("Ingrese el título de la película:");
		nue.setTitulo(sc.nextLine());
		System.out.println("Ingrese la duración de la película (en minutos):");
		if(!sc.hasNextDouble()) {
			System.out.println("Duración inválida. Registro cancelado.");
			sc.close();
			return;
		}
		nue.setDuracion(sc.nextDouble());
		sc.nextLine(); // Consumir el salto de línea
		System.out.println("Seleccione el género de la película:");
		for(Generos g : Generos.values()) {
			System.out.println(g.ordinal() + 1 + ". " + g);
		}
		int generoOpcion = sc.nextInt();
		sc.nextLine(); // Consumir el salto de línea
		if(generoOpcion < 1 || generoOpcion > Generos.values().length) {
			System.out.println("Género inválido. Registro cancelado.");
			sc.close();
			return;
		}
		Generos genero = Generos.values()[generoOpcion - 1];
		nue.setGenero(genero);
		System.out.println("Ingrese el director de la película:");
		nue.setDirector(sc.nextLine());
		peliculaDAO.insertar(nue);
		System.out.println("Película registrada exitosamente.");
		sc.close();
	}
    public Pelicula buscarPeliculaID(List<Pelicula> peliculas, int id) {
    	for (Pelicula p : peliculas) {
    		if(p.getId() == id) {
    			return p;
    		}
    	}
    	return null;
    }
}
