package com.tl2.streaming.util.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.tl2.streaming.dao.FactoryDAO;
import com.tl2.streaming.dao.PeliculaDAO;
import com.tl2.streaming.model.Pelicula;
import com.tl2.streaming.util.comparators.*;
import com.tl2.streaming.util.Verificador;

public final class PeliculaUtil {
	private static PeliculaDAO peliculaDAO = FactoryDAO.getPeliculaDAO();
	private static Scanner sc = new Scanner(System.in);
	
	private PeliculaUtil() {
	}
	
	
	
	public static void listarPeliculasEnOrden(){
		mostrarPeliculas(ordenarPeliculas(obtenerPeliculas()));
	}
	public static List<Pelicula> obtenerPeliculas(){
		return peliculaDAO.obtenerTodo();
	}
	public static List<Pelicula> ordenarPeliculas(List<Pelicula> peliculas) {
		Comparator<Pelicula> comparador = null;
		boolean valido = false;
    	System.out.println("Seleccione el criterio de ordenamiento:");
    	System.out.println("1. Título");
    	System.out.println("2. Duración");
    	System.out.println("3. Género");
    	while (!valido) {
	    	int opcion = Verificador.obtenerInt();
			switch(opcion) {
			case 1:
				comparador = new ComparadorTitulo(); valido= true;
				break;
			case 2:
				comparador = new ComparadorDuracion(); valido = true;
				break;
			case 3:
				comparador = new ComparadorGenero(); valido = true;
				break;
			default:
				System.out.print("Error: debe ingresar un número entre 1 y 3: ");
			}
    	}
		Collections.sort(peliculas, comparador);
		return peliculas;
    }
	
	public static void mostrarPeliculas(List<Pelicula> peliculas) {
		System.out.println("Películas:");
		for(Pelicula p : peliculas) {
			System.out.println(p);
		}
	}
	public static void mostrarTitulos(List<Pelicula> peliculas) {
		int i;
		System.out.println("Títulos");
		for (i=0; i<peliculas.size(); i++) {
			System.out.println(i + " - " + peliculas.get(i).getTitulo());
		}
	}
	
    public static void registrarPelicula() {
    	Pelicula nue = new Pelicula();
		System.out.println("Ingrese el título de la película:");
		nue.setTitulo(sc.nextLine().trim());
		System.out.println("Ingrese la duración de la película (en minutos):");
		nue.setDuracion(Verificador.obtenerDouble());
		System.out.println("Seleccione el género de la película:");
		nue.setGenero(Verificador.obtenerGenero());
		System.out.println("Ingrese el director de la película:");
		nue.setDirector(sc.nextLine().trim());
		System.out.println("Pelicula: " + nue.getTitulo()
			+ "\nDuración: " + nue.getDuracion()
			+ "\nGénero: " + nue.getGenero()
			+ "\nDirector: " + nue.getDirector());
		if(Verificador.confirmar()) {
			peliculaDAO.insertar(nue);
			System.out.println("Película registrada exitosamente.");
		}
		else System.out.println("Cancelando registro de película...");
	}
    public static Pelicula obtenerPelicula(int id) {
    	return peliculaDAO.obtener(id);
    }
}
