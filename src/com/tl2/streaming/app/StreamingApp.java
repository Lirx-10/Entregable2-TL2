package com.tl2.streaming.app;

import com.tl2.streaming.dao.*;

//import com.tl2.streaming.model.*;
//import com.tl2.streaming.util.*;
//import java.sql.*;
import com.tl2.streaming.model.*;

public class StreamingApp{
    public static void main(String[] args) {
        PersonaDAO pd = FactoryDAO.getPersonaDAO();
        Persona p = new Persona("Leo","Reynaga",45297661,21);

        pd.insertar(p);
        String nombre = "Leonel";
        Persona p1 = new Persona();
        p1 = pd.obtenerNombre(nombre);
        if (p1 != null) {
            System.out.println("Nombre: " + p1.getNombre() + " Apellido: " + p1.getApellido());
        } else {
            System.out.println("No se encontró la persona con nombre "+ nombre);
        }

    }
} 