package com.tl2.streaming.util;

import java.sql.*;

public class DatabaseManager {
    /**
* Este metodo se encarga de la creación de las tablas donde se
* almacenara la
* informacion de los objetos. Una vez establecida una conexion, debería
* ser lo proximo a ser ejecutado.
*
* @param connection objeto conexion a la base de datos SQLite
* @throws SQLException
*/
public static void creacionDeTablasEnBD(Connection connection) throws SQLException {
    Statement stmt;
    stmt = connection.createStatement();
    String sql=" CREATE TABLE IF NOT EXISTS DATOS_PERSONALES ("+ "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+ "NOMBRES TEXT(100) NOT NULL,"+"APELLIDO TEXT(100) NOT NULL,"+ "DNI INTEGER NOT NULL"+");";
    stmt.executeUpdate(sql);
    sql=" CREATE TABLE IF NOT EXISTS PELICULA ("+
        "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+"GENERO TEXT(1) NOT NULL,"+
        "TITULO TEXT(100) NOT NULL,"+"RESUMEN TEXT(500),"+
        "DIRECTOR TEXT(100) NOT NULL,"+ "DURACION REAL NOT NULL"+");";
    stmt.executeUpdate(sql);
    sql=" CREATE TABLE IF NOT EXISTS USUARIO ("+
        "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+"NOMBRE_USUARIO TEXT NOT NULL,"+
        "EMAIL TEXT NOT NULL,"+ "CONTRASENIA TEXT NOT NULL, ID_DATOS_PERSONALES INTEGER NOT NULL,"+
        "CONSTRAINT USUARIO_DATOS_PERSONALES_FK FOREIGN KEY (ID) REFERENCES DATOS_PERSONALES(ID)"+");";
    stmt.executeUpdate(sql);
    sql=" CREATE TABLE IF NOT EXISTS RESENIA ("+
        "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+"CALIFICACION INTEGER NOT NULL,"+
        "COMENTARIO TEXT(500),"+"APROBADO INTEGER DEFAULT (1) NOT NULL,"+
        "FECHA_HORA DATETIME NOT NULL,"+"ID_USUARIO INTEGER NOT NULL,"+
        "ID_PELICULA INTEGER NOT NULL,"+"CONSTRAINT RESENIA_USUARIO_FK FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID),"+
        "CONSTRAINT RESENIA_PELICULA_FK FOREIGN KEY (ID_PELICULA) REFERENCES PELICULA(ID)"+");";
    stmt.executeUpdate(sql);
    stmt.close();
}
}
