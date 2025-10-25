package com.tl2.streaming.util.model;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;

import com.tl2.streaming.dao.FactoryDAO;
import com.tl2.streaming.dao.PeliculaDAO;
import com.tl2.streaming.dao.ReseniaDAO;
import com.tl2.streaming.model.Pelicula;
import com.tl2.streaming.model.Resenia;
import com.tl2.streaming.model.Usuario;
import com.tl2.streaming.util.*;

public class ReseniaUtil {
	private ReseniaDAO reseniaDAO;
	private PeliculaUtil utilPelicula;
	public ReseniaUtil() {
		this.reseniaDAO = FactoryDAO.getReseniaDAO();
		utilPelicula = new PeliculaUtil();
	}
	
	public void registrarResenia(ReseniaDAO reseniaDAO, PeliculaDAO peliculaDAO) {
    	Usuario u = verificarUsuario();
		Scanner sc = new Scanner(System.in);
		Resenia nue = new Resenia();
		if(u == null) {
			System.out.println("Usuario no válido. Registro de reseña cancelado.");
			sc.close();
			return;
		}
		List<Pelicula> peliculas = utilPelicula.obtenerPeliculas();
		System.out.println("Seleccione el ID de la pelicula que desea hacer una reseña.");
		utilPelicula.mostrarPeliculas(peliculas);
		int id = sc.nextInt();
		Pelicula p = utilPelicula.buscarPeliculaID(peliculas, id);
		if (p == null) {
			System.out.println("No se encontró la pelicula con el id: "+ id + ". Saliendo...");
			sc.close();
			return;
		}
		System.out.println("Ingrese la calificación (1-5):");
		nue.setCalificacion(sc.nextInt());
		sc.nextLine(); // Consumir el salto de línea
		System.out.println("Ingrese el comentario:");
		nue.setComentario(sc.nextLine());
		nue.setAprobado(0); // Por defecto no aprobado
		nue.setFecha_hora(Instant.now());
		nue.setUsuario(u);
		int opcion;
		do {
			System.out.println("¿Confirmar reseña?");
			System.out.println("0 para confirmar.");
			System.out.println("1 para descartar.");
			opcion = sc.nextInt();
		} while (opcion != 0 && opcion != 1);
		if(opcion == 0) reseniaDAO.insertar(nue);
		sc.close();
    }
	public List<Resenia> obtenerResenias(){
		return reseniaDAO.obtenerTodo();
	}
	public void mostrarReseniasNoAprobadas(List<Resenia> resenias) {
		for(Resenia r : resenias) {
			if(r.getAprobado() == 0) {
				System.out.println(r);
			}
		}
	}
	public Resenia buscarResenia(int id, List<Resenia> resenias) {
		for(Resenia r : resenias) {
			if(r.getId() == id) {
				return r;
			}
		}
		return null;
	}
	public void aprobarResenia() {
		Scanner sc = new Scanner(System.in);				
		List<Resenia> resenias = obtenerResenias();
		System.out.println("Seleccione el ID de la resenia que desea aprobar.");
		mostrarReseniasNoAprobadas(resenias);
		int id = sc.nextInt();
		Resenia re = buscarResenia(id, resenias);
		if(re == null) {
			System.out.println("No se encontró la reseña ");
			sc.close();
			return;
		}
		if(re.getAprobado() != 0) {
			System.out.println("La reseña seleccionada ya esta aprobada.");
			sc.close();
			return;
		}
		else {
			System.out.println(re);
			int opcion;
			do {
				System.out.println("¿Aprobar reseña?");
				System.out.println("0 para confirmar.");
				System.out.println("1 para descartar.");
				opcion = sc.nextInt();
			} while (opcion != 0 && opcion != 1);
			if(opcion == 0) {
				re.setAprobado(1);
				reseniaDAO.modificar(re);
			}
			sc.close();
		}
	}
}
