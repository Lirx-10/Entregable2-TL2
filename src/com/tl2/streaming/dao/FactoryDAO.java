package com.tl2.streaming.dao;

import com.tl2.streaming.dao.jdbc.PersonaDAOjdbc;
import com.tl2.streaming.dao.jdbc.PeliculaDAOjdbc;
import com.tl2.streaming.dao.jdbc.ReseniaDAOjdbc;
import com.tl2.streaming.dao.jdbc.UsuarioDAOjdbc;

public class FactoryDAO {

    public static PersonaDAO getPersonaDAO(){
        return new PersonaDAOjdbc();
    }

    public static UsuarioDAO getUsuarioDAO(){
        return new UsuarioDAOjdbc();
    }

    public static ReseniaDAO getReseniaDAO(){
        return new ReseniaDAOjdbc();
    }

    public static PeliculaDAO getPeliculaDAO(){
        return new PeliculaDAOjdbc();
    }
}
