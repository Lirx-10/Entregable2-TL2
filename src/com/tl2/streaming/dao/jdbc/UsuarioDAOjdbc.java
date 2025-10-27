package com.tl2.streaming.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tl2.streaming.dao.UsuarioDAO;
import com.tl2.streaming.model.Usuario;
import com.tl2.streaming.model.Persona;
import com.tl2.streaming.util.MyConnection;

public class UsuarioDAOjdbc implements UsuarioDAO {

    @Override
    public void insertar(Usuario a) {
        String query = 
            "INSERT INTO USUARIO (NOMBRE_USUARIO, EMAIL, CONTRASEÑA, ID_PERSONA)"+
            "values (?,?,?,?)";
        try {
            Connection conn = MyConnection.getConnection();
            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1, a.getNombreUsuario());
                stmt.setString(2, a.getEmail());
                stmt.setString(3, a.getContrasenia());
                stmt.setInt(4, a.getPersona().getId());
                stmt.executeUpdate();
                System.out.println("Usuario insertado correctamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar Usuario "+ e.getMessage());
        }
    }

    @Override
    public void modificar(Usuario u) {
        String query =                 
                "UPDATE USUARIO SET NOMBRE_USUARIO = ?, EMAIL = ?, CONTRASEÑA = ?"+
                "WHERE ID = ?";
        try {
            Connection conn = MyConnection.getConnection();
            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(4, u.getPersona().getId());
                stmt.setString(1, u.getNombreUsuario());
                stmt.setString(2, u.getEmail());
                stmt.setString(3, u.getContrasenia());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar Usuario "+ e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM USUARIO WHERE ID = ?";
        try {
            Connection conn = MyConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar un Usuario "+e.getMessage());
        }
    }
    
    @Override
    public Usuario obtener(int id) {
        Usuario user = new Usuario();
        String query = "SELECT * FROM USUARIO WHERE ID = ?";
        try {
            Connection conn = MyConnection.getConnection();
            try(PreparedStatement stmt = conn.prepareStatement(query);){
                stmt.setInt(1,id);
                try(ResultSet rs = stmt.executeQuery()){
                    if(rs.next()){
                    	Persona p = new Persona();
                        user.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
                        user.setEmail(rs.getString("EMAIL"));
                        user.setContrasenia(rs.getString("CONTRASEÑA"));    
                        p.setId = rs.getInt("ID_PERSONA");
                        user.setPersona(p);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener Usuario "+e.getMessage());
        }
        return user;
    }
    
    @Override
    public List<Usuario> obtenerTodo() {
        List<Usuario> usuarios = new ArrayList<>();
        
        String query = "SELECT U.ID, U.NOMBRE_USUARIO, U.EMAIL, U.CONTRASEÑA, U.ID_PERSONA, "+
                    "P.NOMBRE, P.APELLIDO, P.DNI, P.EDAD "+
                    "FROM USUARIO U INNER JOIN PERSONA P ON P.ID = U.ID_PERSONA";
        try {
            Connection conn = MyConnection.getConnection();
            try(PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    Usuario user = new Usuario();
                    Persona p = new Persona();
                    user.setId(rs.getInt("ID"));
                    user.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
                    user.setEmail(rs.getString("EMAIL"));
                    user.setContrasenia(rs.getString("CONTRASEÑA"));
                    p.setId(rs.getInt("ID_PERSONA"));
                    p.setNombre(rs.getString("NOMBRE"));
                    p.setApellido(rs.getString("APELLIDO"));
                    p.setDNI(rs.getInt("DNI"));
                    p.setEdad(rs.getInt("EDAD"));
                    user.setPersona(p);
                    usuarios.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los usuarios "+e.getMessage());
        }
        return usuarios;
    }

    @Override
    public int validarUsuario(String u, String c){
        String query = "SELECT ID FROM USUARIO WHERE NOMBRE = ?, CONTRASEÑA = ?";
        int id = 0;
        try {
            Connection conn = MyConnection.getConnection();
            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setString(1, u);
                stmt.setString(2, c);
                try(ResultSet rs = stmt.executeQuery()){
                    if(rs.next()){
                        id = rs.getInt("ID");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al validar el usuario "+e.getMessage());
        }
        return id;
    }

}
