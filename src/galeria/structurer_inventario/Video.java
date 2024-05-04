package galeria.structurer_inventario;

import galeria.structurer_usuarios.Externo;

public class Video extends Pieza {
	private double duracion;
	private String formato;
	private String recursosNecesarios;
	public Video(String titulo, int anio, String lugarCreacion, boolean electricidad, String tiempoDisponible,
			String autor, double duracion, String formato, String recursosNecesarios, Externo externo) {
		super(titulo, anio, lugarCreacion, electricidad, tiempoDisponible, autor, externo);
		this.duracion = duracion;
		this.formato = formato;
		this.recursosNecesarios = recursosNecesarios;
	}
	
	
	
}
