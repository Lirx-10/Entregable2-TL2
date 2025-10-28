package com.tl2.streaming.util.model;

// Librerias java
import java.util.Scanner;
import java.util.List;
// Clases DAO
import com.tl2.streaming.dao.FactoryDAO;
import com.tl2.streaming.dao.UsuarioDAO;
// Clases Modelo
import com.tl2.streaming.model.Persona;
import com.tl2.streaming.model.Usuario;
//Clases Util
import com.tl2.streaming.util.comparators.ComparadorNombre;

public final class UsuarioUtil {

    private UsuarioUtil(){}

    private static final Scanner sc = new Scanner(System.in);
    private static final UsuarioDAO ud = FactoryDAO.getUsuarioDAO();

    public static String verificarEmail(){
        String email;
        boolean valido = false;
            do{
                email = sc.nextLine().trim().toLowerCase();
                if (email.matches("^\\w+@\\w+\\.\\w+$")==false){
                    System.out.println("Ingrese el email nuevamente con formato xxx@yyy.zzz");
                }else{
                    valido = true;
                }
            } while(valido == false);
        return email;
    }

    public static String verificarContrasenia(){
        // Contrasenia con al menos 4 caracteres
        String contrasenia;
        boolean valido = false;
        do{
            contrasenia = sc.nextLine().trim();
            if (contrasenia.length() > 4){
                valido = true;
            }else{
                System.out.println("La contraseña debe de tener mas de 4 caracteres");
                System.out.println("Ingresela nuevamente");
            }
        }while(valido==false);
        return contrasenia;
    }

    public static int seleccionarPersona(){      
        System.out.println("¿Como desea registrar su usario?");
        System.out.println("- Ingrese 1 para agregar una nueva persona");
        System.out.println("- Ingrese 2 para seleccionar una persona existente");
        int opcion = sc.nextInt();
        int id = 0;
        if (opcion == 1){
            // Agregamos una persona nueva
            id = PersonaUtil.ingresarPersona();
        }else if (opcion == 2){
            //traemos las personas y seleccionamos
            PersonaUtil.listarPersonas();
            System.out.println("Ingrese el id de la persona que quiere usar");
            id = sc.nextInt();
        }
        sc.nextLine();
        return id;
    }

    public static void ingresarUsuario(){
        int id_persona;
        Usuario u = new Usuario();
        do{
            id_persona = seleccionarPersona();
            if(id_persona == 0){
                System.out.println("Persona no encontrada, ingrese un id valido");
            }
        }while(id_persona==0);
        Persona p = new Persona();
        u.setPersona(p);
        u.getPersona().setId(id_persona);
        
        int check;
        do{
            System.out.println("Ingrese el nombre de usuario: ");
            u.setNombreUsuario(sc.nextLine().trim());

            System.out.println("Ingrese su email: ");
            u.setEmail(verificarEmail());

            System.out.println("Ingrese su contraseña: ");
            u.setContrasenia(verificarContrasenia());            
        
            System.out.println(u);
            System.out.println("Los datos ingresados son correctos?");
            System.out.println("1: Si son correctos");
            System.out.println("2: No son correctos");
            check = sc.nextInt();
            while (check != 1 && check != 2) {
            	System.out.println("Error: debe solo debe ingresar 1 o 2");
            }
            if(check == 2){
                System.out.println("Ingrese los datos nuevamente");
            }
            sc.nextLine();
        }while(check == 2);   
        ud.insertar(u);
    }

    public static void listarUsuarios(){
        List<Usuario> usuarios = ud.obtenerTodo();
        usuarios.sort(new ComparadorNombre());
        System.out.println("Ahora se imprimen todos los usuarios");
        for (Usuario u: usuarios){
            System.out.println(u);
            System.out.println("->" + u.getPersona());
        }
    }

    public static Usuario verificarUsuario(String nombreUsuario, String contrasenia){
        Usuario u = ud.validarUsuario(nombreUsuario, contrasenia);
        if (u == null){
            System.out.println("El usuario ingresado no existe");
        }
        return u;
    }
}
