package com.tl2.streaming.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.tl2.streaming.dao.PersonaDAO;
import com.tl2.streaming.model.Persona;

public class DAOPersonaJDBC implements PersonaDAO{

    private final String GETONE = "SELECT FROM * PERSONA WHERE ID = ?";

    // Usar un constructor que reciba el parametro conexión es mejor práctica, 1 conexión en
    // el main (o donde sea) que será usada para todos los DAO

    @Override
    public Persona obtener(int id) {
        Persona p = null;
        PreparedStatement stmt = null;
        try {
        } catch (Exception e) {
            
        }
        throw new UnsupportedOperationException("Unimplemented method 'obtener'");
    }

    @Override
    public List<Persona> obtenerTodo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTodo'");
    }

    @Override
    public void insertar(Persona a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public void modificar(Persona a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public void eliminar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }
    
}
