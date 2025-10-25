package com.tl2.streaming.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tl2.streaming.dao.UsuarioDAO;
import com.tl2.streaming.model.Usuario;
import com.tl2.streaming.util.MyConnection;

public class UsuarioDAOjdbc implements UsuarioDAO {

    @Override
    public void insertar(Usuario a) {
        String query = 
            "INSERT INTO USUARIO (NOMBRE_USUARIO, EMAIL, CONTRASEÑA, ID_PERSONA)"+
            "values (?,?,?,?)";
        try {
            Connection conn = MyConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, a.getNombreUsuario());
            stmt.setString(2, a.getEmail());
            stmt.setString(3, a.getContrasenia());
            stmt.setInt(4, a.getIdPersona());
            stmt.executeUpdate();
            System.out.println("Usuario insertado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al insertar Usuario "+ e.getMessage());
        }
    }

    @Override
    public void modificar(Usuario a) {
        try {
            Connection conn = MyConnection.getConnection();
        } catch (SQLException e) {
            System.out.println("Error al modificar Usuario "+ e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        //try {
            //Connection conn = MyConnection.getConnection();
        //} catch (SQLException e) {
          //  System.out.println("Error al eliminar un Usuario "+e.getMessage());
        //}
    }
    
    @Override
    public Usuario obtener(int id) {
        Usuario user = new Usuario();
        //try {
            //Connection conn = MyConnection.getConnection();
            //String query = "SELECT * FROM USUARIO";
            //PreparedStatement stmt = conn.prepareStatement(query);
            
        //} catch (Exception e) {
            // TODO: handle exception
        //}
        return user;
    }
    
    @Override
    public List<Usuario> obtenerTodo() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM USUARIO";
        try {
            Connection conn = MyConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getInt(1));
                user.setNombreUsuario(rs.getString(2));
                user.setContrasenia(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setIdPersona(rs.getInt(5));
                
                usuarios.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los usuarios "+e.getMessage());
        }
        return usuarios;
    }
}
