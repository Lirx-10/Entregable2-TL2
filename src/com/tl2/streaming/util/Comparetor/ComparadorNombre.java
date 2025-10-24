package com.tl2.streaming.util.Comparetor;

import java.util.Comparator;
import com.tl2.streaming.model.Usuario;

public class ComparadorNombre implements Comparator<Usuario>{

    @Override
    public int compare(Usuario arg0, Usuario arg1) {
        return arg0.getNombreUsuario().compareToIgnoreCase(arg1.getNombreUsuario());
    }
    
}
