package com.tl2.streaming.util.model;
// DAO
import com.tl2.streaming.dao.FactoryDAO;
import com.tl2.streaming.dao.PersonaDAO;
// Modelo
import com.tl2.streaming.model.Persona;
// Java util
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public final class PersonaUtil {

    private PersonaUtil(){}
    
    private static final Scanner sc = new Scanner(System.in);
    
    public static boolean validarNombre(String texto) {
        if (texto == null || texto.length()==0){
            return false;  
        }                                                
        return texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }

    public static boolean validarDNI(int DNI){
        PersonaDAO p = FactoryDAO.getPersonaDAO();
        return !p.existeDNI(DNI);
    }

    public static Persona ingresarPersona(){
        Persona p = new Persona();
        int check = 2;
        do{
            String nombre;
            do{
                System.out.println("Ingrese su nombre: ");
                // .trim() quita los espacios en blanco "   Leo   " --> "Leo"
                nombre = sc.nextLine().trim();
                if(!validarNombre(nombre)){
                    System.out.println("Error: El nombre posee numeros o está vacío");
                    System.out.println("El nombre solo debe de poseer letras");
                }
            }while(!validarNombre(nombre));
            p.setNombre(nombre);

            String apellido;
            do{
                System.out.println("Ingrese su apellido: ");
                apellido = sc.nextLine().trim();
                if(!validarNombre(apellido)){
                    System.out.println("Error: El nombre posee numeros");
                    System.out.println("El nombre solo debe de poseer letras");
                }
            }while(!validarNombre(apellido));
            p.setApellido(apellido);

            int DNI;
            boolean valido=false;
            do{ 
                System.out.println("Ingrese su DNI: ");
                // try catch, para manejar la excepcion de ingresar un valor que no sea un entero
                try {
                    DNI = sc.nextInt();
                    if(validarDNI(DNI)==false){
                        System.out.println("Error: Ya existe una persona con el mismo DNI");
                        System.out.println("Ingrese un dni no existente? xd");
                    } else {
                        valido = true;
                        p.setDNI(DNI);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ingresar un número válido");
                    sc.nextLine();//limpiar buffer
                }
            }while(valido == false);
        
            int edad;
            valido = false;
            do{
                System.out.println("Ingrese su edad: ");
                try {
                    edad = sc.nextInt();
                    p.setEdad(edad);
                    valido = true;
                } catch (InputMismatchException e) {
                    System.out.println("Ingresar un número entero válido");
                    sc.nextLine();//limpiar buffer
                }
            }while(valido == false);

            System.out.println(p);
            System.out.println("Los datos ingresados son correctos?");
            System.out.println("1: Si son correctos");
            System.out.println("2: No son correctos");
            check = sc.nextInt();
            if(check == 2){
                System.out.println("Ingrese los datos nuevamente");
            }
        }while(check == 2);
    
        return p;
    }

    public static void listarPersonas(){
        PersonaDAO up = FactoryDAO.getPersonaDAO();
        List<Persona> personas = up.obtenerTodo();
        for(Persona p: personas){
            System.out.println("id:"+p.getId()+" "+p);
        }
    }
}
