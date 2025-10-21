package com.tl2.streaming.app;

import java.util.Scanner;
import com.tl2.streaming.dao.*;
//import com.tl2.streaming.util.*;
import com.tl2.streaming.model.*;
import com.tl2.streaming.util.MyConnection;

public class StreamingApp{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el apellido: ");
        String apellido = sc.nextLine();
        System.out.println("Ingrese el DNI: ");
        int DNI = sc.nextInt();
        System.out.println("Ingrese la edad: ");
        int edad = sc.nextInt();

        Persona p = new Persona(nombre,apellido,DNI,edad);
        PersonaDAO pd = FactoryDAO.getPersonaDAO();
        pd.insertar(p);
        
    }
} 