package com.tl2.streaming.dao.jdbc;
import java.sql.*;
import java.util.List;
import java.util.LinkedList;
import com.tl2.streaming.util.MyConnection;
import com.tl2.streaming.dao.PeliculaDAO;
import com.tl2.streaming.enumer.Generos;
import com.tl2.streaming.model.Pelicula;

public class PeliculaDAOjdbc implements PeliculaDAO {
	public PeliculaDAOjdbc() {
	}
    @Override
    public List<Pelicula> obtenerTodo() {
    	try {
    		Connection conn = MyConnection.getConnection();
    		List<Pelicula> peliculas = new LinkedList<>();
    		try (Statement stmt = conn.createStatement()) {
				String sql = "SELECT * FROM pelicula;";
				try (ResultSet rs = stmt.executeQuery(sql)) {
					while (rs.next()) {
						Pelicula p = new Pelicula();
						p.setId(rs.getInt("id"));
						String nue = rs.getString("genero");
						Generos g = Generos.valueOf(nue);
						p.setGenero(g);
						p.setTitulo(rs.getString("titulo"));
						p.setResumen(rs.getString("resumen"));
						p.setDirector(rs.getString("director"));
						p.setDuracion(rs.getDouble("duracion"));
						peliculas.add(p);
					}
				}
			}
    		return peliculas;
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    @Override
    public Pelicula obtener(int id) {
		try {
			Connection conn = MyConnection.getConnection();
			Pelicula p = new Pelicula();
			try (Statement stmt = conn.createStatement()) {
				String sql = "SELECT * FROM pelicula WHERE id = " + id + ";";
				try (ResultSet rs = stmt.executeQuery(sql)) {
					if (rs.next()) {
						p = new Pelicula();
						p.setId(rs.getInt("id"));
						p.setTitulo(rs.getString("titulo"));
						p.setResumen(rs.getString("resumen"));
						p.setDuracion(rs.getDouble("duracion"));
						p.setDirector(rs.getString("director"));
						String nue = rs.getString("genero");
						Generos g = Generos.valueOf(nue);
						p.setGenero(g);
					}
				}
				
			}
			return p;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    @Override
    public void insertar(Pelicula a) {
		try {
			Connection conn = MyConnection.getConnection();
			try (Statement stmt = conn.createStatement()) {
				String sql = "INSERT INTO pelicula (genero, titulo, resumen, director, duracion) VALUES ('"
						+ a.getGenero().toString() + "', '"
						+ a.getTitulo() + "', '"
						+ a.getResumen() + "', '"
						+ a.getDirector() + "', "
						+ a.getDuracion() + ");";
				stmt.executeUpdate(sql);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    public void modificar(Pelicula a) {
    	try {
    		Connection conn = MyConnection.getConnection();
    		try (Statement stmt = conn.createStatement()) {
				String sql = "UPDATE pelicula SET "
						+ "genero = '" + a.getGenero().toString() + "', "
						+ "titulo = '" + a.getTitulo() + "', "
						+ "resumen = '" + a.getResumen() + "', "
						+ "director = '" + a.getDirector() + "', "
						+ "duracion = " + a.getDuracion() + " "
						+ "WHERE id = " + a.getId() + ";";
				stmt.executeUpdate(sql);
    		}
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    public void eliminar(int id) {
		try {
			Connection conn = MyConnection.getConnection();
			try (Statement stmt = conn.createStatement()) {
				String sql = "DELETE FROM pelicula WHERE id = " + id + ";";
				stmt.executeUpdate(sql);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
    }
}
