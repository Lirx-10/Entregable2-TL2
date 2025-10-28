package com.tl2.streaming.dao.jdbc;
import java.sql.*;
import java.time.LocalDateTime;
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
				String sql = "SELECT r.id, r.calificacion, r.comentario, r.aprobado, r.fecha_hora, r.id_usuario, "
						+ " p.id AS id_pelicula, p.genero, p.titulo, p.resumen, p.director, p.duracion"
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
							r.setFecha_hora(ts.toLocalDateTime());
						}
						Pelicula p = new Pelicula();
						p.setId(rs.getInt(7));
						String gen = rs.getString(8);
						p.setGenero(Generos.valueOf(gen));
						p.setTitulo(rs.getString(9));
						p.setResumen(rs.getString(10));
						p.setDirector(rs.getString(11));
						p.setDuracion(rs.getDouble(12));
						r.setPelicula(p);
						Usuario u = new Usuario();
						u.setId(rs.getInt(6));
						r.setUsuario(u);
						resenias.add(r);
					}
				}
			}
			return resenias;
    	}
    	catch (Exception e) {
    		System.out.println("Error al obtener todas las reseñas..." + e.getMessage());
    		return null;
    	}
    }

    @Override
    public Resenia obtener(int id) {
    	try {
    		Connection conn = MyConnection.getConnection();
			Resenia r = null;
			try (Statement stmt = conn.createStatement()) {
				String sql = "SELECT r.id, r.calificacion, r.comentario, r.aprobado, r.fecha_hora, r.id_usuario,"
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
							r.setFecha_hora(ts.toLocalDateTime());
						}
                        Usuario u = new Usuario();
                        u.setId(rs.getInt(6));
						Pelicula p = new Pelicula();
						p.setId(rs.getInt(7));
						String gen = rs.getString(8);
						p.setGenero(Generos.valueOf(gen));
						p.setTitulo(rs.getString(9));
						p.setResumen(rs.getString(10));
						p.setDirector(rs.getString(11));
						p.setDuracion(rs.getDouble(12));
						r.setPelicula(p);
                        r.setUsuario(u);
					}
				}
			}
			return r;
    	}
    	catch (Exception e) {
            System.out.println("Error al obtener la reseña. " + e.getMessage());
			return null;
		}
    }

    @Override
    public void insertar(Resenia a) {
    	try {
    		Connection conn = MyConnection.getConnection();
			try (Statement stmt = conn.createStatement()) {
                Timestamp ts = Timestamp.valueOf(a.getFecha_hora());
				String sql = "INSERT INTO resenia (id_pelicula, id_usuario, calificacion, comentario, aprobado, fecha_hora) VALUES ('"
						+ a.getPelicula().getId() + "', '"
						+ a.getUsuario().getId() + "', '"
						+ a.getCalificacion() + "', '"
						+ a.getComentario() + "', '"
						+ a.getAprobado() + "', '"
						+ ts + "');";
				stmt.executeUpdate(sql);
			}
		}
    	catch (Exception e) {
            System.out.println("Error al insertar la reseña en la base de datos. " + e.getMessage());
		}
    }

    @Override
    public void modificar(Resenia a) {
        String sql = "UPDATE resenia SET "
                + "calificacion = ?, "
                + "comentario = ?, "
                + "aprobado = ?, "
                + "fecha_hora = ?, "
                + "id_usuario = ?, "
                + "id_pelicula = ? "
                + "WHERE id = ?";
        try{
            Connection conn = MyConnection.getConnection();
            Timestamp ts = Timestamp.valueOf(a.getFecha_hora());
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, a.getCalificacion());
                pstmt.setString(2, a.getComentario());
                pstmt.setInt(3, a.getAprobado());
                pstmt.setTimestamp(4,ts);
                pstmt.setInt(5, a.getUsuario().getId());
                pstmt.setInt(6, a.getPelicula().getId());
                pstmt.setInt(7, a.getId());
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error al modificar la reseña: " + e.getMessage());
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
            System.out.println("Error al eliminar la reseña de la base de datos. "+ e.getMessage());
    	}
    	
    } 
}
