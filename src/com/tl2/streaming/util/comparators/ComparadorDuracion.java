package com.tl2.streaming.util.comparators;
import java.util.Comparator;
import com.tl2.streaming.model.Pelicula;

public class ComparadorDuracion implements Comparator<Pelicula> {

	public ComparadorDuracion() {
	}
	@Override
	public int compare(Pelicula p1, Pelicula p2) {
		return Double.compare(p1.getDuracion(), p2.getDuracion());
	}
}
