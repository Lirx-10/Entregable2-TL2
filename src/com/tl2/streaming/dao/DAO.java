package com.tl2.streaming.dao;

import java.util.List;

public interface DAO<T> {
   
    //void insertar(T a);

    //void modificar(T a);

    //void eliminar(int id);

    List<T> obtenerTodo();

    T obtener(int id);

    
}
