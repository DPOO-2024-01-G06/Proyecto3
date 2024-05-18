package galeria.structurer_inventario;


import java.util.List;

import java.util.Map;

import galeria.structurer_usuarios.Propietario;

public class Video extends Pieza{
	/**
	 * 
	 */
	private static final long serialVersionUID = 529374543344217327L;
	private float duracion; 
	private String formato;
	private String recursosNecesarios;
	private String type = "video";
	public Video() {}
	public Video(String titulo, String fecha, String lugarCreacion, boolean electricidad, String tiempoDisponible,
			float duracion, String formato, String recursosNecesarios, Propietario propietario, Artista artista, Map<String, List<String>> historial, Venta venta) {
		super(titulo, fecha, lugarCreacion, electricidad, tiempoDisponible, propietario, artista, historial, venta);
		this.duracion = duracion;
		this.formato = formato;
		this.recursosNecesarios = recursosNecesarios;
	}
	public float getDuracion() {
		return duracion;
	}
	public String getFormato() {
		return formato;
	}
	public String getRecursosNecesarios() {
		return recursosNecesarios;
	}
	public String getType() {
		return type;
	} 
}
