package com.tl2.streaming.model;

public class Usuario extends Persona{
    private int id_usuario;
    private String nombreUsuario;
    private String contrasenia;
    private String email;

    public Usuario(){}
    
    public Usuario(String nombre, String apellido, int DNI, int edad,String nombreUsuario,String email,String contrasenia){
        super(nombre,apellido,DNI,edad);
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasenia = contrasenia;
    } 

    @Override
    public String toString(){
        return super.toString()+" Nombre de usuario: "+nombreUsuario+" Email: "+email +" Contraseña: "+contrasenia;
    }

    public int getId() {
        return id_usuario;
    }
    public void setId(int id) {
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