package com.tl2.streaming.util.comparators;
import java.util.Comparator;
import com.tl2.streaming.model.Pelicula;

public class ComparadorPorGenero implements Comparator<Pelicula> {

	public ComparadorPorGenero() {
	}
	@Override
	public int compare(Pelicula p1, Pelicula p2) {
		String genero1 = p1.getGenero().toString();
		String genero2 = p2.getGenero().toString();
		return genero1.compareToIgnoreCase(genero2);
	}
}
