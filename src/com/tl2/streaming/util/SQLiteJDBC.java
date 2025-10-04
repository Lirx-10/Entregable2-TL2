package com.tl2.streaming.util;

import java.sql.*;

public class SQLiteJDBC {
    private static final String URL = "jdbc:sqlite:database/streaming.db";

    public static Connection getConnection() throws SQLException{
        //Agregar para diagnosticar un problema mas especifico
        // es necesario?
        return DriverManager.getConnection(URL);
    }

    public static void inicializarBaseDatos(){
        // El try catch se utiliza cuando algo puede fallar, es como un if else, intento hacer la conexión 
        // (Connection conn = getConnection()) y si lo hago me tira el mensaje de todo ok, si no me tira el error y cual es
        // Por eso definimos otro getConnection() throws SQLException que se encarga de detectar los errores,
        // que es algo que puede ocurrir
        try (Connection conn = getConnection()){
            System.out.println("Base de datos conectada/creada"+ URL);
        }
        catch(SQLException e){
            System.out.println("Error al inicializar la base de datos"+ e.getMessage());
        }
    }
}