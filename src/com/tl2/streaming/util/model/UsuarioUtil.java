package com.tl2.streaming.util.model;

// Librerias java
import java.util.Scanner;
import java.util.List;
// Clases DAO
import com.tl2.streaming.dao.FactoryDAO;
import com.tl2.streaming.dao.PersonaDAO;
import com.tl2.streaming.dao.UsuarioDAO;
import com.tl2.streaming.dao.jdbc.PersonaDAOjdbc;
// Clases Modelo
import com.tl2.streaming.model.Persona;
import com.tl2.streaming.model.Usuario;
//Clases Util
import com.tl2.streaming.util.comparators.ComparadorNombre;
import com.tl2.streaming.util.model.PersonaUtil;

public final class UsuarioUtil {

    private UsuarioUtil(){}

    private static final Scanner sc = new Scanner(System.in);

    public static boolean verificarEmail(String email){
        return email.matches("^\\w+@\\w+\\.\\w+$");
    }

    public static boolean verificarContrasenia(String contrasenia){
        // Contrasenia con al menos 4 caracteres
        if (contrasenia.length() < 4){
            return false;
        }else{
            return true;
        }
    }

    public static int seleccionarPersona(){      
        System.out.println("¿Como desea registrar su usario?");
        System.out.println("- Ingrese 1 para agregar una nueva persona");
        System.out.println("- Ingrese 2 para seleccionar una persona existente");
        int opcion = sc.nextInt();
        int id = 0;
        if (opcion == 1){
            // Agregamos una persona nueva
            Persona p = PersonaUtil.ingresarPersona();
            PersonaDAO pd = FactoryDAO.getPersonaDAO();
            pd.insertar(p);
            Persona per = pd.obtenerNombre(p.getNombre());
            id = per.getId();
        }else if (opcion == 2){
            //traemos las personas y seleccionamos
            PersonaDAO pd = FactoryDAO.getPersonaDAO();
            List<Persona> personas = pd.obtenerTodo();
            PersonaUtil.listarPersonas(personas);
            System.out.println("Ingrese el id de la persona que quiere usar");
            id = sc.nextInt();
        }
        sc.nextLine();
        return id;
    }

    public static void ingresarUsuario(){
        int id_persona;
        do{
            id_persona = seleccionarPersona();
            if(id_persona == 0){
                System.out.println("Persona no encontrada, ingrese un id valido");
            }
        }while(id_persona==0);
        Usuario u = new Usuario();
        u.setId(id_persona);

        System.out.println("Ingrese el nombre de usuario: ");
        String nombreUsuario = sc.nextLine().trim();
        u.setNombreUsuario(nombreUsuario);

        String email;
        do{
            System.out.println("Ingrese su email: ");
            email = sc.nextLine().trim();
            if (verificarEmail(email)==false){
                System.out.println("Ingrese el email nuevamente con formato xxx@yyy");
            }else{
                u.setEmail(email);
            }
        } while(verificarEmail(email) == false);

        String contrasenia;
        do{
            System.out.println("Ingrese su contraseña: ");
            contrasenia = sc.nextLine().trim();
            if (verificarContrasenia(contrasenia)==true){
                u.setContrasenia(contrasenia);
            }
        }while(verificarContrasenia(contrasenia)==false);
        
        UsuarioDAO ud = FactoryDAO.getUsuarioDAO();
        ud.insertar(u);

    }
    
    public static void listarUsuarios(List<Usuario> usuarios){
        usuarios.sort(new ComparadorNombre());
        System.out.println("Ahora se imprimen todos los usuarios");
        for (Usuario u: usuarios){
            System.out.println(u);
        }
    }

}
