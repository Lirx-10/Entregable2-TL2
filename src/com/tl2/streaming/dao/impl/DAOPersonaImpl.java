package com.tl2.streaming.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.tl2.streaming.dao.PersonaDAO;
import com.tl2.streaming.util.MyConnection;
import com.tl2.streaming.model.Persona;

public class DAOPersonaImpl extends MyConnection implements PersonaDAO{

    private final String GETONE = "SELECT FROM * PERSONA WHERE ID = ?";

    @Override
    public List<Persona> obtenerTodo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTodo'");
    }

    @Override
    public Persona obtener(Long id) {
        // TODO Auto-generated method stub
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona p = null;
        throw new UnsupportedOperationException("Unimplemented method 'obtener'");
    }
    
}
