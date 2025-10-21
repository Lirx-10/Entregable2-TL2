package com.tl2.streaming.dao.jdbc;
import java.util.List;
import java.util.LinkedList;
import com.tl2.streaming.dao.PeliculaDAO;
import com.tl2.streaming.model.Pelicula;
import com.tl2.streaming.util.MyConnection;
import com.tl2.streaming.enumer.Generos;
import java.sql.*;


public class PeliculaDAOjdbc implements PeliculaDAO {

    @Override
    public List<Pelicula> obtenerTodo() {
    	try (Connection conn = MyConnection.getConnection()) {
			try (Statement stmt = conn.createStatement()){
				String sql = "SELECT * FROM PELICULA;";
				try (ResultSet rs = stmt.executeQuery(sql)){
					List<Pelicula> peliculas = new LinkedList<Pelicula>();
					while (rs.next()) {
						Pelicula p = new Pelicula();
						p.setId(rs.getInt("id"));
						p.setGenero(Generos.valueOf(rs.getString("genero")));
						p.setTitulo(rs.getString("titulo"));
						p.setResumen(rs.getString("resumen"));
						p.setDirector(rs.getString("director"));
						p.setDuracion(rs.getDouble("duracion"));
						peliculas.add(p);
					}
					return peliculas;
				}
			}
    	}
    	catch (SQLException e) {
			e.printStackTrace();
			return null;
    	}
    }

    @Override
    public Pelicula obtener(int id) {
    	try (Connection conn = MyConnection.getConnection()) {
			try (Statement stmt = conn.createStatement()){
				String sql = "SELECT * FROM PELICULA WHERE id = " + id + ";";
				try (ResultSet rs = stmt.executeQuery(sql)){
					if (rs.next()) {
						Pelicula p = new Pelicula();
						p.setId(rs.getInt("id"));
						p.setGenero(Generos.valueOf(rs.getString("genero")));
						p.setTitulo(rs.getString("titulo"));
						p.setResumen(rs.getString("resumen"));
						p.setDirector(rs.getString("director"));
						p.setDuracion(rs.getDouble("duracion"));
						return p;
					}
					else {
						return null;
					}
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }

    @Override
    public void insertar(Pelicula a) {
    	try (Connection conn = MyConnection.getConnection()) {
			try (Statement stmt = conn.createStatement()){
				String sql = "INSERT INTO PELICULA (genero, titulo, resumen, director, duracion) VALUES ('" 
						+ a.getGenero().toString() + "', '" 
						+ a.getTitulo() + "', '"
						+ a.getResumen() + "', '" 
						+ a.getDirector() + "', " 
						+ a.getDuracion() + ");";
				stmt.executeUpdate(sql);
			}
		}
    	catch (SQLException e) {
			e.printStackTrace();
    	}
    }

    @Override
    public void modificar(Pelicula a) {
    	try (Connection conn = MyConnection.getConnection()) {
    		try (Statement stmt = conn.createStatement()){
    			String sql = "UPDATE PELICULA SET "
    					+ "genero = '" + a.getGenero().toString() + "', "
    					+ "titulo = '" + a.getTitulo() + "', "
    					+ "resumen = '" + a.getResumen() + "', "
    					+ "director = '" + a.getDirector() + "', "
    					+ "duracion = " + a.getDuracion() + " "
    					+ "WHERE id = " + a.getId() + ";";
    			stmt.executeUpdate(sql);
    		}
    	}
    	catch (SQLException e) {
			e.printStackTrace();
    	}
    }

    @Override
    public void eliminar(int id) {
    	try (Connection conn = MyConnection.getConnection()) {
			try (Statement stmt = conn.createStatement()){
				String sql = "DELETE FROM PELICULA WHERE id = " + id + ";";
				stmt.executeUpdate(sql);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
}
