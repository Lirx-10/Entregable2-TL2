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
    private static final PersonaDAO pd = FactoryDAO.getPersonaDAO();

    public static boolean noTieneNumeros(String texto) {
        if (texto == null || texto.length()==0){
            return false;  
        }                                                
        return texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }
    public static String validarNombre(){
        String nombre;
            do{
                // .trim() quita los espacios en blanco "   Leo   " --> "Leo"
                nombre = sc.nextLine().trim();
                if(noTieneNumeros(nombre)==false){
                    System.out.println("Error: El nombre posee numeros o está vacío");
                    System.out.println("El nombre solo debe de poseer letras, ingreselo nuevamente");
                }
            }while(noTieneNumeros(nombre)==false);
        return nombre;
    }
    public static int validarDNI(){
        int DNI=0;
        boolean valido=false;
        do{ // try catch, para manejar la excepcion de ingresar un valor que no sea un entero
            try {
                DNI = sc.nextInt();
                if(pd.existeDNI(DNI)==true){
                    System.out.println("Error: Ya existe una persona con el mismo DNI");
                    System.out.println("Ingrese su DNI nuevamente");
                } else {
                    valido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingresar un número válido");
            }
            sc.nextLine();//limpiar buffer
        } while(valido == false);
        return DNI;
    }

    public static int validarEdad(){
        int edad=0;
            boolean valido = false;
            do{
                System.out.println("Ingrese su edad: ");
                try {
                    edad = sc.nextInt();
                    if(edad>0 && edad<120){
                        valido = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ingresar un número entero válido");
                    sc.nextLine();//limpiar buffer
                }
            }while(valido == false);
        return edad;
    }

    public static void registrarPersona(){
        Persona p = new Persona();
        int check = 2;
        do{
            System.out.println("Ingrese su nombre: ");
            p.setNombre(validarNombre());
            System.out.println("Ingrese su apellido: ");
            p.setApellido(validarNombre());
            System.out.println("Ingrese su DNI: ");
            p.setDNI(validarDNI()); 
            p.setEdad(validarEdad());
            System.out.println(p);
            System.out.println("Los datos ingresados son correctos?");
            System.out.println("1: Si son correctos");
            System.out.println("2: No son correctos");
            check = sc.nextInt();
            if(check == 2){
                System.out.println("Ingrese los datos nuevamente");
            }
        }while(check == 2);
        
        pd.insertar(p);
    }

    public static int ingresarPersona(){
        Persona p = new Persona();
        int check = 2;
        do{
            System.out.println("Ingrese su nombre: ");
            p.setNombre(validarNombre());
            System.out.println("Ingrese su apellido: ");
            p.setApellido(validarNombre());
            System.out.println("Ingrese su DNI: ");
            p.setDNI(validarDNI()); 

            System.out.println(p);
            System.out.println("Los datos ingresados son correctos?");
            System.out.println("1: Si son correctos");
            System.out.println("2: No son correctos");
            check = sc.nextInt();
            if(check == 2){
                System.out.println("Ingrese los datos nuevamente");
            }
        }while(check == 2);
        
        pd.insertar(p);
        return pd.obtenerId(p.getNombre());
    }

    public static void listarPersonas(){
        List<Persona> personas = pd.obtenerTodo();
        for(Persona p: personas){
            System.out.println("id:"+p.getId()+" "+p);
        }
    }
}
