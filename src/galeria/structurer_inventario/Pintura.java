package galeria.structurer_inventario;

import galeria.structurer_usuarios.Externo;

public class Pintura extends Pieza {
	private double ancho;
	private double alto;
	private String tipoLienzo;
	
	public Pintura(String titulo, int anio, String lugarCreacion, boolean electricidad, String tiempoDisponible,
			String autor, double ancho, double alto, String tipoLienzo, Externo externo) {
		super(titulo, anio, lugarCreacion, electricidad, tiempoDisponible, autor, externo);
		this.ancho = ancho;
		this.alto = alto;
		this.tipoLienzo = tipoLienzo;
	}

}
