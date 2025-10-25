package com.tl2.streaming.model;

public class Usuario extends Persona{
    private int id_usuario;
    private String nombreUsuario;
    private String contrasenia;
    private String email;

    public Usuario(){}
    
    public Usuario(String nombre, String apellido, int DNI, int edad,String nombreUsuario,String contrasenia,String email){
        super(nombre,apellido,DNI,edad);
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.email = email;
    } 

    @Override
    public String toString(){
        return "Nombre de usuario: "+nombreUsuario+" Email: "+email;
    }

    public int getIdUsuario() {
        return id_usuario;
    }
    public void setIdUsuario(int id) {
        this.id_usuario = id;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }   
}