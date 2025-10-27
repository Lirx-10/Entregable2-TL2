package com.tl2.streaming.util.model;

import java.time.Instant;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.tl2.streaming.dao.FactoryDAO;
import com.tl2.streaming.dao.PeliculaDAO;
import com.tl2.streaming.dao.ReseniaDAO;
import com.tl2.streaming.dao.UsuarioDAO;
import com.tl2.streaming.model.Pelicula;
import com.tl2.streaming.model.Resenia;
import com.tl2.streaming.model.Usuario;
import com.tl2.streaming.util.*;

public final class ReseniaUtil {
	private static ReseniaDAO reseniaDAO = FactoryDAO.getReseniaDAO();
	private static UsuarioDAO usuarioDAO = FactoryDAO.getUsuarioDAO();
	private ReseniaUtil() {
	}
	
	private static int obtenerInt(Scanner sc) {
		boolean valido = false;
		int i = 0;
		while (!valido) {
			try {
                i = sc.nextInt();
                valido = true;  // Si llega aquí, es un entero válido
            }
			catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un número entero.");
                sc.nextLine();  // Limpiar el buffer del scanner
            }
		}
		return i;
	}
	private static boolean confirmar(Scanner sc) {
		 String respuesta;
		 sc.nextLine();
	     do {
	    	 System.out.print("¿Deseas confirmar la operación? (S/N): ");
	    	 respuesta = sc.nextLine().trim().toUpperCase();
	     } while (!respuesta.equals("S") && !respuesta.equals("N"));
	     if(respuesta.equals("S")) return true;
	     else return false;
	}
	public static void registrarResenia(ReseniaDAO reseniaDAO, PeliculaDAO peliculaDAO) {
    	Usuario u = verificarUsuario();
		Scanner sc = new Scanner(System.in);
		Resenia nue = new Resenia();
		if(u == null) {
			System.out.println("Usuario no válido. Registro de reseña cancelado.");
			sc.close();
			return;
		}
		List<Pelicula> peliculas = PeliculaUtil.obtenerPeliculas();
		PeliculaUtil.mostrarTitulos(peliculas);
		System.out.print("Seleccione el número de la película a la cual desea hacer una reseña: ");
		int pos = obtenerInt(sc);
		while(pos < 0 || pos >= peliculas.size()) {
			System.out.println("Error: debe ingresar un número entre 0 y " + peliculas.size());
			pos = obtenerInt(sc);
		}
		nue.setPelicula(peliculas.get(pos));
		System.out.println("Ingrese la calificación (1-5):");
		int cal = obtenerInt(sc);
		while (cal < 1 || cal > 5) {
			System.out.println("Error: ingrese una valor entre 1 y 5.");
			cal = obtenerInt(sc);
		}
		nue.setCalificacion(cal);
		System.out.println("Ingrese el comentario:");
		nue.setComentario(sc.nextLine());
		nue.setAprobado(0); // Por defecto no aprobado
		nue.setFecha_hora(Instant.now());
		nue.setUsuario(u);
		if(confirmar(sc)) reseniaDAO.insertar(nue);
		sc.close();
    }
	public static List<Resenia> obtenerResenias(){
		List<Resenia> lista = reseniaDAO.obtenerTodo();
//		for(Resenia r : lista) {
//			int idUsuario = r.getUsuario().getId();
//			r.setUsuario(usuarioDAO.obtener(idUsuario));
//		}
		return lista;
	}
	public static void mostrarReseniasNoAprobadas(List<Resenia> resenias) {
		for(Resenia r : resenias) {
			if(r.getAprobado() == 0) {
				System.out.println(r);
			}
		}
	}
	public static Resenia buscarResenia(int id, List<Resenia> resenias) {
		for(Resenia r : resenias) {
			if(r.getId() == id) {
				return r;
			}
		}
		return null;
	}
	public static void aprobarResenia() {
		Scanner sc = new Scanner(System.in);				
		List<Resenia> resenias = obtenerResenias();
		mostrarReseniasNoAprobadas(resenias);
		System.out.print("Seleccione el ID de la reseña que desea aprobar: ");
		int id = obtenerInt(sc);
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
			boolean conf = confirmar(sc);
			if(conf) {
				re.setAprobado(1);
				reseniaDAO.modificar(re);
			}
			sc.close();
		}
	}
}
