package com.tl2.streaming.model;

import com.tl2.streaming.enumer.*;

public class Usuario{
    private int id;
    private String nombreUsuario;
    private String contrasenia;
    private String email;
    private Planes plan;
    private Idiomas idioma;
    private boolean emailVerificado;

    public Usuario(){}
    
    public Usuario(String nombre,String contrasenia,String email,Planes plan,Idiomas idioma){
        this.nombreUsuario = nombre;
        this.contrasenia = contrasenia;
        this.email = email;
        this.plan = plan;

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
    public Planes getPlan() {
        return plan;
    }
    public void setPlan(Planes plan) {
        this.plan = plan;
    }
    public Idiomas getIdioma() {
        return idioma;
    }
    public void setIdioma(Idiomas idioma) {
        this.idioma = idioma;
    }
    public boolean isEmailVerificado() {
        return emailVerificado;
    }
    public void setEmailVerificado(boolean emailVerificado) {
        this.emailVerificado = emailVerificado;
    }
   
}