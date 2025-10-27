package com.tl2.streaming.model;

public class Usuario extends Persona{
    private int id;
    private String nombreUsuario;
    private String contrasenia;
    private String email;
    private int id_persona;

    public Usuario(){}
    
    public Usuario(String nombre, String apellido, int DNI, int edad,String nombreUsuario,String email,String contrasenia){
        super(nombre,apellido,DNI,edad);
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasenia = contrasenia;
    } 

    @Override
    public String toString(){
        return "Nombre de usuario: "+nombreUsuario+" Email: "+email +" Contraseña: "+contrasenia;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public int getIdPersona(){
        return id_persona;
    }

    public void setIdPersona(int id_persona){
        this.id_persona = id_persona;
    }
}