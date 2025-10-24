package com.tl2.streaming.util;

import java.util.List;
import com.tl2.streaming.model.Usuario;
import com.tl2.streaming.util.Comparetor.*;

public class UsuarioUtil {

    public boolean verificarEmail(String email){
        return email.matches("^\\w+@\\w+\\.\\w+$");
    }

    
    public static void listarUsuarios(List<Usuario> usuarios){
        usuarios.sort(new ComparadorNombre());
        for (Usuario u: usuarios){
            System.out.println(u);
        }
    }
}
