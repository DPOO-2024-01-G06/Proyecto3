package galeria.structurer_inventario;

import java.util.List;

public class Artista {
	public String nombre;
	private List<Pieza> piezas;
	public Artista(){}

	public Artista(String nombre, List<Pieza> piezas){
		this.nombre = nombre;	
		this.piezas = piezas;
	}

	public String getNombre() {
		return nombre;
	}
	public void addPieza(Pieza pieza) {
		piezas.add(pieza);
	}
	
	public List<Pieza> getPiezas(){
		return piezas;
	}
	public void setPiezas(List<Pieza> piezas){
		this.piezas = piezas;
	}
}
