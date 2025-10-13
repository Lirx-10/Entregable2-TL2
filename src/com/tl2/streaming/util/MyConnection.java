package com.tl2.streaming.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static Connection Conexion = null;
     
    public static Connection getConnection() throws SQLException{
        //Agregar para diagnosticar un problema mas especifico
        if (Conexion == null){
            Conexion = DriverManager.getConnection("jdbc:sqlite:database/streaming.db");
        }
        return Conexion;
    }

    private MyConnection(){}
}
