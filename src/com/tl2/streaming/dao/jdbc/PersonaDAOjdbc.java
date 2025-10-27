package com.tl2.streaming.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.tl2.streaming.dao.PersonaDAO;
import com.tl2.streaming.model.Persona;
import com.tl2.streaming.util.MyConnection;

public class PersonaDAOjdbc implements PersonaDAO{


    @Override
    public void insertar(Persona a) {
        String query = 
                "INSERT INTO PERSONA(NOMBRE, APELLIDO, DNI, EDAD)"+ 
                "values (?, ?, ?, ?)";
        try {
            Connection conn = MyConnection.getConnection();
            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1, a.getNombre());
                stmt.setString(2, a.getApellido());
                stmt.setInt(3, a.getDNI());
                stmt.setInt(4, a.getEdad());
                stmt.executeUpdate();
            }
            System.out.println("Persona insertada correctamente");
        } catch (SQLException e) {
            System.out.println("Error papito " + e.getMessage());
        }
    }

    @Override
    public void modificar(Persona p) {
        String query = 
                "UPDATE PERSONA SET NOMBRE = ?, APELLIDO = ?, DNI = ?, EDAD = ?"+
                "WHERE ID = ?";
        try {
            Connection conn = MyConnection.getConnection();
            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(5, p.getId());
                stmt.setString(1, p.getNombre());
                stmt.setString(2, p.getApellido());
                stmt.setInt(3, p.getDNI());
                stmt.setInt(4, p.getEdad());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar Persona "+e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM PERSONA WHERE ID = ?";
        try {
            Connection conn = MyConnection.getConnection();
            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar Persona "+e.getMessage());
        }
    }

    @Override
    public Persona obtener(int id) {
        Persona p = new Persona();
        String query = "SELECT * FROM PERSONA WHERE ID = ?;";
        try {
            Connection conn = MyConnection.getConnection();
            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(1, id);
                try(ResultSet rs = stmt.executeQuery();){
                    if(rs.next()){
                        p = new Persona();
                        p.setId(rs.getInt("ID"));
                        p.setNombre(rs.getString("NOMBRE"));
                        p.setApellido(rs.getString("APELLIDO"));
                        p.setDNI(rs.getInt("DNI"));
                        p.setEdad(rs.getInt("EDAD"));
                    }
                }
            }
        }catch (SQLException e) {
            System.out.println("Error al obtener Persona "+ e.getMessage());
        }
        return p;
    }

    @Override
    public List<Persona> obtenerTodo() {
        List<Persona> personas = new ArrayList<Persona>();
        String query = "SELECT * FROM PERSONA";
        try {
            Connection conn = MyConnection.getConnection();
            try(PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    Persona p = new Persona();
                    p.setId(rs.getInt(1));
                    p.setNombre(rs.getString(2));
                    p.setApellido(rs.getString(3));
                    p.setDNI(rs.getInt(4));
                    p.setEdad(rs.getInt(5));
                    personas.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al obtener todas las personas "+ e.getMessage());
        }
        return personas;
    }

    public int obtenerId(String nombre){
        int id = 0;
        String query = "SELECT ID FROM PERSONA WHERE NOMBRE = ?;";
        try {
            Connection conn = MyConnection.getConnection();
            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1,nombre);
                try(ResultSet rs = stmt.executeQuery()){
                    if (rs.next()){
                        id = rs.getInt("ID");
                    }
                }
            }      
        } catch (SQLException e) {
            System.out.println("Hubo un error "+ e.getMessage());
        }
        if (id == 0){
            System.out.println("El usuario no existe");
        }
        return id;
    }

    @Override
    public boolean existeDNI(int dni) {
        String query = "SELECT COUNT(*) FROM PERSONA WHERE DNI = ?";
        try {
            Connection conn = MyConnection.getConnection();
            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(1, dni);
                try(ResultSet rs = stmt.executeQuery();){
                    if(rs.next()){  
                        int count = rs.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("error: "+e.getMessage());
        }
        return false;
    }
}
