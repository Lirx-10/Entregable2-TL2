package com.tl2.streaming.model;

public class Persona {
    private int id_persona;
    private String nombre;
    private String apellido;
    private int DNI;
    private int edad;

    public Persona(){}
    public Persona(String n, String a, int DNI, int edad){
        this.nombre = n;
        this.apellido = a;
        this.DNI = DNI;
        this.edad = edad;
    }

    @Override
    public String toString(){
        return this.nombre +" "+ this.apellido +" "+ this.DNI +" "+ this.edad;
    }

    public int getIdPersona(){
        return id_persona;
    }
    public void setIdPersona(int id){
        this.id_persona = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public int getDNI() {
        return DNI;
    }
    public void setDNI(int dNI) {
        DNI = dNI;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    } 
}
