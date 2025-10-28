package com.tl2.streaming.dao.jdbc;
import java.sql.*;
import java.util.List;
import java.util.LinkedList;
import com.tl2.streaming.util.MyConnection;
import com.tl2.streaming.dao.ReseniaDAO;
import com.tl2.streaming.model.*;
import com.tl2.streaming.enumer.*;

public class ReseniaDAOjdbc implements ReseniaDAO{

    @Override
    public List<Resenia> obtenerTodo() {
    	try {
    		Connection conn = MyConnection.getConnection();
			List<Resenia> resenias = new LinkedList<Resenia>();
    		try (Statement stmt = conn.createStatement()) {
				String sql = "SELECT r.id, r.calificacion, r.comentario, r.aprobado, r.fecha_hora,"
						+ " p.id AS id_pelicula, p.genero, p.titulo, p.resumen, p.director, p.duracion, r.id_usuario"
						+ " FROM resenia r INNER JOIN pelicula p ON r.id_pelicula = p.id;";
				try (ResultSet rs = stmt.executeQuery(sql)) {
					while (rs.next()) {
						Resenia r = new Resenia();
						r.setId(rs.getInt(1));
						r.setCalificacion(rs.getInt(2));
						r.setComentario(rs.getString(3));
						r.setAprobado(rs.getInt(4));
						Timestamp ts = rs.getTimestamp(5);
						if (ts != null) {
							r.setFecha_hora(ts.toInstant());
						}
						Pelicula p = new Pelicula();
						p.setId(rs.getInt(6));
						String gen = rs.getString(7);
						p.setGenero(Generos.valueOf(gen));
						p.setTitulo(rs.getString(8));
						p.setResumen(rs.getString(9));
						p.setDirector(rs.getString(10));
						p.setDuracion(rs.getDouble(11));
						r.setPelicula(p);
						Usuario u = new Usuario();
						u.setId(rs.getInt(12));
						r.setUsuario(u);
						resenias.add(r);
					}
				}
			}
			return resenias;
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    @Override
    public Resenia obtener(int id) {
    	try {
    		Connection conn = MyConnection.getConnection();
			Resenia r = null;
			try (Statement stmt = conn.createStatement()) {
				String sql = "SELECT r.id, r.calificacion, r.comentario, r.aprobado, r.fecha_hora,"
						+ " p.id AS id_pelicula, p.genero, p.titulo, p.resumen, p.director, p.duracion"
						+ " FROM resenia r INNER JOIN pelicula p ON r.id_pelicula = p.id WHERE r.id = "+id+ ";";
				try (ResultSet rs = stmt.executeQuery(sql)) {
					if (rs.next()) {
						r = new Resenia();
						r.setId(rs.getInt(1));
						r.setCalificacion(rs.getInt(2));
						r.setComentario(rs.getString(3));
						r.setAprobado(rs.getInt(4));
						Timestamp ts = rs.getTimestamp(5);
						if (ts != null) {
							r.setFecha_hora(ts.toInstant());
						}
						Pelicula p = new Pelicula();
						p.setId(rs.getInt(6));
						String gen = rs.getString(7);
						p.setGenero(Generos.valueOf(gen));
						p.setTitulo(rs.getString(8));
						p.setResumen(rs.getString(9));
						p.setDirector(rs.getString(10));
						p.setDuracion(rs.getDouble(11));
						r.setPelicula(p);
					}
				}
			}
			return r;
    	}
    	catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    @Override
    public void insertar(Resenia a) {
    	try {
    		Connection conn = MyConnection.getConnection();
			try (Statement stmt = conn.createStatement()) {
				String sql = "INSERT INTO resenia (id_pelicula, id_usuario, calificacion, comentario, aprobado, fecha_hora) VALUES ('"
						+ a.getPelicula().getId() + "', '"
						+ a.getUsuario().getId() + "', '"
						+ a.getCalificacion() + "', '"
						+ a.getComentario() + "', '"
						+ a.getAprobado() + "', '"
						+ a.getFecha_hora() + "');";
				stmt.executeUpdate(sql);
			}
		}
    	catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    public void modificar(Resenia a) {
    	try {
    		Connection conn = MyConnection.getConnection();
    		try (Statement stmt = conn.createStatement()){
    			String sql = "UPDATE resenia SET "
    					+ "calificacion = " + a.getCalificacion() + ", "
    					+ "comentario = " + a.getComentario() + ", "
    					+ "aprobado = " + a.getAprobado() + ", "
    					+ "fecha_hora = " + a.getFecha_hora() + ", "
    					+ "id_usuario = " + a.getUsuario().getId() + ", "
    					+ "id_pelicula = " + a.getPelicula().getId() + " "
    					+ "WHERE id = " + a.getId() + ";";
    			stmt.executeUpdate(sql);
    		}
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    }

    @Override
    public void eliminar(int id) {
    	try {
    		Connection conn = MyConnection.getConnection();
    		try (Statement stmt = conn.createStatement()){
    			String sql = "DELETE FROM resenia WHERE id = " + id + ";";
    			stmt.executeUpdate(sql);
    		}
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    	
    } 
}
