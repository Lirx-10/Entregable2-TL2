package com.tl2.streaming.app;

import java.util.Scanner;
import com.tl2.streaming.dao.*;
import com.tl2.streaming.util.*;
import com.tl2.streaming.model.*;
import com.tl2.streaming.util.MyConnection;
import com.tl2.streaming.util.model.PeliculaUtil;
import com.tl2.streaming.util.model.PersonaUtil;
import com.tl2.streaming.util.model.ReseniaUtil;

public class StreamingApp{
    public static void main(String[] args) { int opcion;
    	Scanner sc = new Scanner(System.in);
    	do {
	        System.out.println("\n=== MENÚ PRINCIPAL ===");
	        System.out.println("1. Registrar Persona");
	        System.out.println("2. Registrar Usuario");
	        System.out.println("3. Registrar Película");
	        System.out.println("4. Listar Usuarios");
	        System.out.println("5. Listar Películas");
	        System.out.println("6. Registrar Reseña");
	        System.out.println("7. Aprobar Reseña");
	        System.out.println("0. Salir");
	        System.out.print("Seleccione una opción: ");
	        opcion = sc.nextInt();
	        sc.nextLine(); // limpiar buffer
	
	        switch (opcion) {
	            case 1 -> registrarPersona();
	            case 2 -> registrarUsuario();
	            case 3 -> registrarPelicula();
	            case 4 -> listarUsuarios();
	            case 5 -> listarPeliculas();
	            case 6 -> registrarResenia();
	            case 7 -> aprobarResenia();
	            case 0 -> System.out.println("Saliendo del sistema...");
	            default -> System.out.println("Opción inválida. Intente nuevamente.");
	        }
	    } while (opcion != 0);
    }
    
    public static void limpiarConsola() {
        try {
            String sistema = System.getProperty("os.name");

            if (sistema.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }

        } catch (Exception e) {
            System.out.println("No se pudo limpiar la consola.");
        }
    }
}