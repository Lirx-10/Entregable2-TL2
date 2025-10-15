package com.tl2.streaming.app;

import com.tl2.streaming.dao.FactoryDAO;
import com.tl2.streaming.dao.PersonaDAO;
//import com.tl2.streaming.model.*;
//import com.tl2.streaming.util.*;
//import java.sql.*;
import com.tl2.streaming.model.*;

public class StreamingApp{
    public static void main(String[] args) {
        PersonaDAO pd = FactoryDAO.getPersonaDAO();
        Persona p = new Persona("Leo","Reynaga",45297661,21);

        pd.insertar(p);

        Persona p1 = new Persona();
        p1 = pd.obtener(1);

        System.out.println("Nombre: " + p1.getNombre() +" Apellido: " + p1.getApellido());
    }
}