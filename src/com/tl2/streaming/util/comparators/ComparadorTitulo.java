package com.tl2.streaming.util.comparators;
import java.util.Comparator;
import com.tl2.streaming.model.Pelicula;

public class ComparadorTitulo implements Comparator<Pelicula> {
	
	public ComparadorTitulo() {
	}
	@Override
	public int compare(Pelicula p1, Pelicula p2) {
		return p1.getTitulo().compareToIgnoreCase(p2.getTitulo());
	}
}
