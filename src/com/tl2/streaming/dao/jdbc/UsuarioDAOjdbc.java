package com.tl2.streaming.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.tl2.streaming.dao.UsuarioDAO;
import com.tl2.streaming.model.Usuario;
import com.tl2.streaming.util.MyConnection;

public class UsuarioDAOjdbc implements UsuarioDAO {

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
                user.setId(rs.getInt("ID"));
                user.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
                user.setContrasenia(rs.getString("CONTRASENIA"));
                user.setEmail(rs.getString("EMAIL"));
                
                usuarios.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
        }
        return usuarios;
    }

    @Override
    public Usuario obtener(int id) {
        Usuario user = new Usuario();
        try {
            Connection conn = MyConnection.getConnection();
            String query = "SELECT * FROM USUARIO";
            PreparedStatement stmt = conn.prepareStatement(query);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return user;
    }

    @Override
    public void insertar(Usuario a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public void modificar(Usuario a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public void eliminar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }
    
}
