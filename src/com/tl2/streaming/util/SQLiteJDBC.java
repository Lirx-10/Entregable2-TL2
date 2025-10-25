package com.tl2.streaming.util;
import java.sql.*;

public class SQLiteJDBC {

    public static void inicializarBaseDatos(){
        try{
            Connection conn = MyConnection.getConnection();
            Statement st = conn.createStatement();
            String codigoSQL;

            codigoSQL = 
                "CREATE TABLE IF NOT EXISTS PERSONA ("+
                "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                "NOMBRE TEXT(100) NOT NULL,"+
                "APELLIDO TEXT(100) NOT NULL,"+
                "DNI INTEGER NOT NULL,"+
                "EDAD INTEGER NOT NULL"+
                ");";
            st.executeUpdate(codigoSQL);

            codigoSQL = 
                "CREATE TABLE IF NOT EXISTS PELICULA ("+
                "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                "GENERO TEXT(20) NOT NULL,"+
                "TITULO TEXT(100) NOT NULL,"+
                "RESUMEN TEXT(500) NOT NULL,"+
                "DIRECTOR TEXT(100) NOT NULL,"+
                "DURACION REAL NOT NULL"+
                ");";
            st.executeUpdate(codigoSQL);

            codigoSQL = 
                "CREATE TABLE IF NOT EXISTS USUARIO ("+
                "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                "NOMBRE_USUARIO TEXT NOT NULL,"+
                "EMAIL TEXT NOT NULL,"+
                "CONTRASEÑA TEXT NOT NULL,"+
                "ID_PERSONA INTEGER NOT NULL,"+
                "FOREIGN KEY (ID_PERSONA) REFERENCES PERSONA(ID)"+
                ");";
            st.executeUpdate(codigoSQL);

            codigoSQL = 
                "CREATE TABLE IF NOT EXISTS RESENIA("+
                "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                "CALIFICACION INTEGER NOT NULL,"+
                "COMENTARIO TEXT(500),"+
                "APROBADO INTEGER (1) NOT NULL,"+
                "FECHA_HORA DATETIME NOT NULL,"+
                "ID_USUARIO INTEGER NOT NULL,"+
                "ID_PELICULA INTEGER NOT NULL,"+
                "FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID),"+
                "FOREIGN KEY (ID_PELICULA) REFERENCES PELICULA(ID)"+
                ");";
            st.executeUpdate(codigoSQL);
            st.close();
            System.out.println("Base de datos conectada/creada ");
        }
        catch(SQLException e){
            System.out.println("Error al inicializar la base de datos "+ e.getMessage());
        }
    }
}