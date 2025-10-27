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
	private static Scanner sc = new Scanner(System.in);
	private ReseniaUtil() {
	}
	
	private static int obtenerInt() {
		boolean valido = false;
		int i = 0;
		while (!valido) {
			try {
                i = sc.nextInt();
                valido = true;  // Si llega aquí, es un entero válido
//                sc.nextLine();  // Limpiar el buffer del scanner
            }
			catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un número entero.");
//                sc.nextLine();  // Limpiar el buffer del scanner
            }
			sc.nextLine();
		}
		return i;
	}
	private static boolean confirmar() {
		 String respuesta;
	     do {
	    	 System.out.print("¿Deseas confirmar la operación? (S/N): ");
	    	 respuesta = sc.nextLine().trim().toUpperCase();
	     } while (!respuesta.equals("S") && !respuesta.equals("N"));
	     if(respuesta.equals("S")) {
	    	 System.out.println("Operación confirmada.");
	    	 return true;
	     }
	     else{
	    	 System.out.println("Operación cancelada.");
	    	 return false;
	     }
	}
	public static void registrarResenia() {
		System.out.println("Ingrese su nombre de usuario: ");
		String nombre = sc.nextLine().trim();
		System.out.println("Ingrese la contraseña: ");
		String contrasenia = sc.nextLine().trim();
		int idUsuario = PersonaUtil.validarUsuario(nombre, contrasenia);
		if(idUsuario == 0) {
			System.out.println("Usuario no válido. Registro de reseña cancelado.");
			return;
		}
		Resenia nue = new Resenia();
    	Usuario u = new Usuario();
    	u.setId(idUsuario);
		List<Pelicula> peliculas = PeliculaUtil.obtenerPeliculas();
		PeliculaUtil.mostrarTitulos(peliculas);
		System.out.print("Seleccione el número de la película a la cual desea hacer una reseña: ");
		int pos = obtenerInt();
		while(pos < 0 || pos >= peliculas.size()) {
			System.out.println("Error: debe ingresar un número entre 0 y " + peliculas.size());
			pos = obtenerInt();
		}
		nue.setPelicula(peliculas.get(pos));
		System.out.println("Ingrese la calificación (1-5):");
		int cal = obtenerInt();
		while (cal < 1 || cal > 5) {
			System.out.println("Error: ingrese una valor entre 1 y 5.");
			cal = obtenerInt();
		}
		nue.setCalificacion(cal);
		System.out.println("Ingrese el comentario:");
		nue.setComentario(sc.nextLine());
		nue.setAprobado(0); // Por defecto no aprobado
		nue.setFecha_hora(Instant.now());
		nue.setUsuario(u);
		if(confirmar()) reseniaDAO.insertar(nue);
    }
	public static List<Resenia> obtenerResenias(){
		List<Resenia> lista = reseniaDAO.obtenerTodo();
		for(Resenia r : lista) {
			int idUsuario = r.getUsuario().getId();
			r.setUsuario(usuarioDAO.obtener(idUsuario));
		}
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
		List<Resenia> resenias = obtenerResenias();
		mostrarReseniasNoAprobadas(resenias);
		System.out.print("Seleccione el ID de la reseña que desea aprobar: ");
		int id = obtenerInt();
		Resenia re = buscarResenia(id, resenias);
		if(re == null) {
			System.out.println("No se encontró la reseña ");
			return;
		}
		if(re.getAprobado() != 0) {
			System.out.println("La reseña seleccionada ya esta aprobada.");
			return;
		}
		else {
			System.out.println(re);
			boolean conf = confirmar();
			if(conf) {
				re.setAprobado(1);
				reseniaDAO.modificar(re);
			}
		}
	}
}
