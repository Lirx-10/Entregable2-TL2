package com.tl2.streaming.app;

//import com.tl2.streaming.model.*;
import com.tl2.streaming.util.*;
//import java.sql.*;

public class StreamingApp{
    public static void main(String[] args) {
        System.out.println("Iniciando la plataforma de Streaming");
        SQLiteJDBC.inicializarBaseDatos();
    }
}