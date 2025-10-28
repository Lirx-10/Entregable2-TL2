package com.tl2.streaming.util.model;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;

import com.tl2.streaming.dao.FactoryDAO;
import com.tl2.streaming.dao.ReseniaDAO;
import com.tl2.streaming.dao.UsuarioDAO;
import com.tl2.streaming.model.Pelicula;
import com.tl2.streaming.model.Resenia;
import com.tl2.streaming.model.Usuario;
import com.tl2.streaming.util.Verificador;

public final class ReseniaUtil {
	private static ReseniaDAO reseniaDAO = FactoryDAO.getReseniaDAO();
	private static UsuarioDAO usuarioDAO = FactoryDAO.getUsuarioDAO();
	private static Scanner sc = new Scanner(System.in);
	private ReseniaUtil() {
	}
	
	public static void registrarResenia() {
		System.out.println("Ingrese su nombre de usuario: ");
		String nombre = sc.nextLine().trim();
		System.out.println("Ingrese la contraseña: ");
		String contrasenia = sc.nextLine().trim();
    	Usuario u = UsuarioUtil.verificarUsuario(nombre, contrasenia);
		if(u == null) {
			System.out.println("Usuario no válido. Registro de reseña cancelado.");
			return;
		}
		Resenia nue = new Resenia();
		nue.setUsuario(u);
		List<Pelicula> peliculas = PeliculaUtil.obtenerPeliculas();
		PeliculaUtil.mostrarTitulos(peliculas);
		System.out.print("Seleccione el número de la película a la cual desea hacer una reseña: ");
		int pos = Verificador.obtenerInt();
		while(pos < 0 || pos >= peliculas.size()) {
			System.out.println("Error: debe ingresar un número entre 0 y " + peliculas.size());
			pos = Verificador.obtenerInt();
		}
		nue.setPelicula(peliculas.get(pos));
		System.out.println("Ingrese la calificación (1-5):");
		int cal = Verificador.obtenerInt();
		while (cal < 1 || cal > 5) {
			System.out.println("Error: ingrese una valor entre 1 y 5.");
			cal = Verificador.obtenerInt();
		}
		nue.setCalificacion(cal);
		System.out.println("Ingrese el comentario:");
		nue.setComentario(sc.nextLine());
		nue.setAprobado(0); // Por defecto no aprobado
		nue.setFecha_hora(Instant.now());
		nue.setUsuario(u);
		if(Verificador.confirmar()) reseniaDAO.insertar(nue);
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
		int id = Verificador.obtenerInt();
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
			boolean conf = Verificador.confirmar();
			if(conf) {
				re.setAprobado(1);
				reseniaDAO.modificar(re);
			}
		}
	}
}
