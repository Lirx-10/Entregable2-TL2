package com.tl2.streaming.dao;

import java.util.List;

public interface DAO<T , K> {
   
    //void insertar(T a);

    //void modificar(T a);

    //void eliminar(T a);

    List<T> obtenerTodo();

    T obtener(K a);

    
}
