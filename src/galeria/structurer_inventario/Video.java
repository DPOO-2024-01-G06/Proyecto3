package galeria.structurer_inventario;

import java.util.List;
import java.util.Map;

import galeria.structurer_usuarios.Externo;

public class Video extends Pieza {
	private double duracion;
	private String formato;
	private String recursosNecesarios;
	public Video(String titulo, String fecha, String lugarCreacion, boolean electricidad, String tiempoDisponible,
			String autor, double duracion, String formato, String recursosNecesarios, Externo externo, Artista artista, Map<String, List<String>> historial) {
		super(titulo, fecha, lugarCreacion, electricidad, tiempoDisponible, externo, artista, historial);
		this.duracion = duracion;
		this.formato = formato;
		this.recursosNecesarios = recursosNecesarios;
	}
	
	
	
}
