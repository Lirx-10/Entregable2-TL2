package com.tl2.streaming.dao;

import com.tl2.streaming.model.Usuario;

public interface UsuarioDAO extends DAO<Usuario>{
  
    public int validarUsuario(String nombreUsuario, String contrasenia);

} 
