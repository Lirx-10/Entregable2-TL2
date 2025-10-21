package com.tl2.streaming.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import com.tl2.streaming.dao.PersonaDAO;
import com.tl2.streaming.model.Persona;
import com.tl2.streaming.util.MyConnection;

public class PersonaDAOjdcb implements PersonaDAO{

    public Persona obtenerNombre(String nombre){
        Persona p = null;
        try (Connection conn = MyConnection.getConnection()){
            String query = "SELECT * FROM PERSONA WHERE NOMBRE = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                p = new Persona();
                p.setNombre(rs.getString("NOMBRE"));
                p.setApellido(rs.getString("APELLIDO"));
                p.setDNI(rs.getInt("DNI"));
                p.setEdad(rs.getInt("EDAD"));
                p.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Hubo un error "+ e.getMessage());
        }
        return p;
    }

    public Persona obtener(int id) {
        Persona p = null;
        try {
            Connection conn = MyConnection.getConnection();
            String query = "SELECT * FROM PERSONA WHERE ID = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                p = new Persona();
                p.setNombre(rs.getString("NOMBRE"));
                p.setApellido(rs.getString("APELLIDO"));
                p.setDNI(rs.getInt("DNI"));
                p.setEdad(rs.getInt("EDAD"));
                p.setId(rs.getInt(1));
            }
        }catch (SQLException e) {
            System.out.println("Hubo un error "+ e.getMessage());
        }
        return p;
    }

    @Override
    public List<Persona> obtenerTodo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTodo'");
    }

    @Override
    public void insertar(Persona a) {
        try {
            Connection conn = MyConnection.getConnection();
            String query = 
                "INSERT INTO PERSONA(NOMBRE, APELLIDO, DNI, EDAD)"+ 
                "values (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, a.getNombre());
            stmt.setString(2, a.getApellido());
            stmt.setInt(3, a.getDNI());
            stmt.setInt(4, a.getEdad());
            stmt.executeUpdate();
            System.out.println("Persona insertada correctamente");
        } catch (SQLException e) {
            System.out.println("Error papito " + e.getMessage());
        }
    }

    @Override
    public void modificar(Persona a) {
        try {
            Connection conn = MyConnection.getConnection();
            String query = "";
        } catch (SQLException e) {
            // TODO: handle exception
        }
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public void eliminar(int id) {
        try {
            Connection conn = MyConnection.getConnection();
            String query = "DELETE FROM PERSONA WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeQuery();
        } catch (SQLException e) {
            // TODO: handle exception
        }
        
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

}
