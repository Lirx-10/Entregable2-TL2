package com.tl2.streaming.model;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contrasenia;
    private String email;

    public Usuario(){}
    
    public Usuario(String nombre,String contrasenia,String email){
        this.nombreUsuario = nombre;
        this.contrasenia = contrasenia;
        this.email = email;
    } 

    @Override
    public String toString(){
        return "Nombre de usuario: "+nombreUsuario+" Email: "+email;
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