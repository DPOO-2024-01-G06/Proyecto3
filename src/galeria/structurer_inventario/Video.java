package galeria.structurer_inventario;

import java.util.List;
import java.util.Map;
import galeria.structurer_usuarios.Propietario;

public class Video extends Pieza {
	private double duracion;
	private String formato;
	private String recursosNecesarios;
	public Video(String titulo, String fecha, String lugarCreacion, boolean electricidad, String tiempoDisponible,
			String autor, double duracion, String formato, String recursosNecesarios, Propietario propietario, Artista artista, Map<String, List<String>> historial, Venta venta) {
		super(titulo, fecha, lugarCreacion, electricidad, tiempoDisponible, propietario, artista, historial, venta);
		this.duracion = duracion;
		this.formato = formato;
		this.recursosNecesarios = recursosNecesarios;
	}
	public double getDuracion() {
		return duracion;
	}
	public String getFormato() {
		return formato;
	}
	public String getRecursosNecesarios() {
		return recursosNecesarios;
	}	
}
