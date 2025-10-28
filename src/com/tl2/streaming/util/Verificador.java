package com.tl2.streaming.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.tl2.streaming.enumer.Generos;

public final class Verificador {
	private static Scanner sc = new Scanner(System.in);

	private Verificador() {
	}
	
	public static double obtenerDouble() {
		boolean valido = false;
		double i = 0;
		while (!valido) {
			try {
                i = sc.nextDouble();
                valido = true;  // Si llega aquí, es un entero válido
            }
			catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un número con el formato [xx.xx].");
            }
			sc.nextLine();  // Limpiar el buffer del scanner
		}
		return i;
	}
	public static int obtenerInt() {
		boolean valido = false;
		int i = 0;
		while (!valido) {
			try {
                i = sc.nextInt();
                valido = true;  // Si llega aquí, es un entero válido
            }
			catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un número entero.");
            }
            sc.nextLine();  // Limpiar el buffer del scanner
		}
		return i;
	}
	public static Generos obtenerGenero() {
		Generos genero = null;
		boolean valido = false;
		int generoOpcion;
		for(Generos g : Generos.values()) {
			System.out.println(g.ordinal() + 1 + ". " + g);
		}
		while(!valido) {
			generoOpcion = obtenerInt();
			if(generoOpcion > 0 && generoOpcion <= Generos.values().length) {
				genero = Generos.values()[generoOpcion-1];
				valido = true;
			}
			else System.out.println("Error: debe ingresar un número entre 1 y " + Generos.values().length);
		}
		return genero;
	}
	public static boolean confirmar() {
		 String respuesta;
	     do {
	    	 System.out.print("¿Deseas confirmar la operación? (S/N): ");
	    	 respuesta = sc.nextLine().trim().toUpperCase();
	     } while (!respuesta.equals("S") && !respuesta.equals("N"));
	     if(respuesta.equals("S")) {
	    	 System.out.println("Operación confirmada.");
	    	 return true;
	     }
	     else{
	    	 System.out.println("Operación cancelada.");
	    	 return false;
	     }
	}
	
}
