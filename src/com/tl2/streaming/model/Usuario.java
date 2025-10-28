package com.tl2.streaming.model;

public class Usuario{
    private int id;
    private String nombreUsuario;
    private String contrasenia;
    private String email;
    private Persona persona;

    public Usuario(){}
    
    public Usuario(String nombreUsuario,String email,String contrasenia, Persona persona){
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasenia = contrasenia;
        this.persona = persona;
    } 

    @Override
    public String toString(){
        return "Nombre de usuario: " + nombreUsuario + " Email: " + email +" Contraseña: " + contrasenia;
    }

    public void setPersona(Persona persona){
        this.persona = persona;
    }

    public Persona getPersona(){
        return persona;
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
}